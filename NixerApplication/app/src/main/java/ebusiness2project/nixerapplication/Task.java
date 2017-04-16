package ebusiness2project.nixerapplication;

public class Task {
    private int taskID;
    private String title;
    private float price;
    private String description;
    private String location;
    private String category;

    //creates the task table in the database

    public Task() {
    }

    public Task(String title, float price, String description, String location, String category) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.location = location;
        this.category = category;
        //setting entered information to this class' information
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //all of these functions set and return the corresponding information

    @Override
    public String toString() {
        return  "Task Number: " + taskID + "\n" +
                "Title: " + title + "\n" +
                "Price: €" + price + "\n" +
                "Description: " + description + "\n" +
                "Location: " + location + "\n" +
                "Category: " + category + "\n";

        //turns all the information to the necessary string layout for returning
    }
}