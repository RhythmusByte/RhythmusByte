class SoftwareEngineer {
  private String name;
  private String role;
  private String[] languagesSpoken;

  public SoftwareEngineer() {
    this.name = "Akhil Mahesh";
    this.role = "Student";
    this.languagesSpoken = new String[]{"English", "Malayalam", "Tamil"}; 
  }

  public void sayHi() {
    System.out.println("Thanks for dropping by, hope you find my profile interesting!");
  } 

  public static void main(String[] args) {
    SoftwareEngineer me = new SoftwareEngineer();
    me.sayHi();
  }
}
