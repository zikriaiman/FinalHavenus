package empowerment;

// ChecklistItem.java
public class ChecklistItem {
    private String text;
    private boolean isChecked;

    public ChecklistItem(String text, boolean isChecked) {
        this.text = text;
        this.isChecked = isChecked;
    }

    public String getText() {
        return text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}

