public class Path {

    private String skill;
    private String jobRole;
    private String nextSkills;

    public Path() {
    }

    public Path(String skill, String jobRole, String nextSkills) {
        this.skill = skill;
        this.jobRole = jobRole;
        this.nextSkills = nextSkills;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getNextSkills() {
        return nextSkills;
    }

    public void setNextSkills(String nextSkills) {
        this.nextSkills = nextSkills;
    }
}