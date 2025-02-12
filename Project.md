# Correct the file path in Cell 2 before using!!!

I'll help you modify the code to use TPU and update the directory path. Since we're using only the training data, we'll split it internally for validation.

**Cell 1 - TPU Setup and Imports:**
```python
# Initialize TPU
import tensorflow as tf
try:
    tpu = tf.distribute.cluster_resolver.TPUClusterResolver()
    print('Running on TPU ', tpu.master())
except ValueError:
    tpu = None

if tpu:
    tf.config.experimental_connect_to_cluster(tpu)
    tf.tpu.experimental.initialize_tpu_system(tpu)
    strategy = tf.distribute.TPUStrategy(tpu)
    print("Number of accelerators: ", strategy.num_replicas_in_sync)
else:
    print("TPU not found")

# Mount Google Drive and import other libraries
from google.colab import drive
drive.mount('/content/drive')

from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Dense, Flatten, Dropout
from tensorflow.keras.optimizers import Adam
import os
import numpy as np
from sklearn.model_selection import train_test_split
import shutil
```

**Cell 2 - Define Constants and Paths:**
```python
# Define constants
IMG_SIZE = 200
BATCH_SIZE = 32 * strategy.num_replicas_in_sync  # Adjust batch size for TPU
EPOCHS = 20
VALIDATION_SPLIT = 0.2

# Set up paths
DATASET_PATH = '/content/drive/MyDrive/Project/dataset_train' # change this path according to the drive
TEMP_DIR = '/content/temp_split'
MODEL_SAVE_DIR = '/content/drive/MyDrive/Project/model'

# Create model save directory if it doesn't exist
os.makedirs(MODEL_SAVE_DIR, exist_ok=True)
```

**Cell 3 - Define Split Function:**
```python
def create_train_val_split(dataset_path, temp_dir, val_split=0.2):
    """
    Creates temporary directories with train/validation split
    """
    # Create temporary directories
    temp_train_dir = os.path.join(temp_dir, 'train')
    temp_val_dir = os.path.join(temp_dir, 'val')
    
    # Get all classes
    classes = os.listdir(dataset_path)
    print(f"Found {len(classes)} classes: {classes}")
    
    # Create directories for each class
    for class_name in classes:
        os.makedirs(os.path.join(temp_train_dir, class_name), exist_ok=True)
        os.makedirs(os.path.join(temp_val_dir, class_name), exist_ok=True)
        
        # Get all images in the class
        class_path = os.path.join(dataset_path, class_name)
        images = [f for f in os.listdir(class_path) if f.endswith(('.png', '.jpg', '.jpeg'))]
        print(f"Processing class {class_name}: {len(images)} images")
        
        # Split images into train and validation
        train_images, val_images = train_test_split(
            images, 
            test_size=val_split, 
            random_state=42
        )
        
        # Copy images to temporary directories
        for img in train_images:
            src = os.path.join(class_path, img)
            dst = os.path.join(temp_train_dir, class_name, img)
            shutil.copy2(src, dst)
            
        for img in val_images:
            src = os.path.join(class_path, img)
            dst = os.path.join(temp_val_dir, class_name, img)
            shutil.copy2(src, dst)
    
    return temp_train_dir, temp_val_dir

# Create train/validation split
print("\nCreating train/validation split...")
temp_train_dir, temp_val_dir = create_train_val_split(DATASET_PATH, TEMP_DIR, VALIDATION_SPLIT)
```

**Cell 4 - Create Data Generators:**
```python
# Create data generators
print("\nSetting up data generators...")
train_datagen = ImageDataGenerator(
    rescale=1./255,
    rotation_range=20,
    width_shift_range=0.2,
    height_shift_range=0.2,
    shear_range=0.2,
    zoom_range=0.2,
    horizontal_flip=True,
    fill_mode='nearest'
)

val_datagen = ImageDataGenerator(rescale=1./255)

# Create data generators
train_generator = train_datagen.flow_from_directory(
    temp_train_dir,
    target_size=(IMG_SIZE, IMG_SIZE),
    batch_size=BATCH_SIZE,
    class_mode='categorical',
    color_mode='rgb'
)

validation_generator = val_datagen.flow_from_directory(
    temp_val_dir,
    target_size=(IMG_SIZE, IMG_SIZE),
    batch_size=BATCH_SIZE,
    class_mode='categorical',
    color_mode='rgb'
)

num_classes = len(train_generator.class_indices)
print(f"\nNumber of classes detected: {num_classes}")
```

**Cell 5 - Create and Compile Model with TPU Strategy:**
```python
with strategy.scope():
    # Create model
    model = Sequential([
        Conv2D(32, (3, 3), activation='relu', input_shape=(IMG_SIZE, IMG_SIZE, 3)),
        MaxPooling2D(2, 2),
        
        Conv2D(64, (3, 3), activation='relu'),
        MaxPooling2D(2, 2),
        
        Conv2D(128, (3, 3), activation='relu'),
        MaxPooling2D(2, 2),
        
        Conv2D(128, (3, 3), activation='relu'),
        MaxPooling2D(2, 2),
        
        Flatten(),
        Dense(512, activation='relu'),
        Dropout(0.5),
        Dense(num_classes, activation='softmax')
    ])

    # Compile model
    model.compile(
        optimizer=Adam(learning_rate=0.0001),
        loss='categorical_crossentropy',
        metrics=['accuracy']
    )

# Print model summary
model.summary()
```

**Cell 6 - Train Model:**
```python
# Train the model
print("\nTraining the model...")
history = model.fit(
    train_generator,
    epochs=EPOCHS,
    validation_data=validation_generator,
    callbacks=[
        tf.keras.callbacks.EarlyStopping(
            patience=3,
            restore_best_weights=True,
            verbose=1
        ),
        tf.keras.callbacks.ModelCheckpoint(
            os.path.join(MODEL_SAVE_DIR, 'keras_model.h5'),
            save_best_only=True,
            verbose=1
        )
    ]
)
```

**Cell 7 - Save Labels and Clean Up:**
```python
# Save the labels
print("\nSaving class labels...")
class_indices = train_generator.class_indices
labels = {v: k for k, v in class_indices.items()}

with open(os.path.join(MODEL_SAVE_DIR, 'labels.txt'), 'w') as f:
    for i in range(len(labels)):
        f.write(f"{labels[i]}\n")

print("Labels saved:", labels)

# Clean up temporary directories
print("\nCleaning up temporary files...")
shutil.rmtree(TEMP_DIR)
```

**Cell 8 - Plot Results:**
```python
# Plot training results
import matplotlib.pyplot as plt

plt.figure(figsize=(12, 4))

plt.subplot(1, 2, 1)
plt.plot(history.history['accuracy'], label='Training Accuracy')
plt.plot(history.history['val_accuracy'], label='Validation Accuracy')
plt.title('Model Accuracy')
plt.xlabel('Epoch')
plt.ylabel('Accuracy')
plt.legend()

plt.subplot(1, 2, 2)
plt.plot(history.history['loss'], label='Training Loss')
plt.plot(history.history['val_loss'], label='Validation Loss')
plt.title('Model Loss')
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.legend()

plt.tight_layout()
plt.show()

print("\nTraining complete! Model and labels saved in:", MODEL_SAVE_DIR)
```

Key changes made:
1. Added TPU initialization and strategy
2. Updated the dataset path to `/content/drive/MyDrive/Project/dataset_train`
3. Adjusted batch size for TPU optimization
4. Wrapped model creation and compilation in TPU strategy scope
5. Updated model save directory to `/content/drive/MyDrive/Project/model`

To use this:
6. Create a new Colab notebook
7. Select Runtime > Change runtime type > TPU > v2-8
8. Copy each cell into your notebook
9. Run the cells in order
