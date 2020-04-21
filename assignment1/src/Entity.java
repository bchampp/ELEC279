public class Entity {
    private String name;
    private Date born;

    public Entity(String name, Date born) {
        this.name = name;
        this.born = born;
    }

    public void setEntity(String name, Date born) {
        this.name = name;
        this.born = born;
    }

    public String getName() {
        return name;
    }

    public Date getBorn() {
        return new Date(born);
    }

    public String toString() {
        return String.format("%s, born on %s", name, born.toString());
    }

    public boolean equals(Entity otherEntity) {
        if (otherEntity == null)
            return false;
        else
            return (name == otherEntity.getName() && born == otherEntity.getBorn());
    }
}