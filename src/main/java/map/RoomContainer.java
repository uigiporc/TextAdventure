package map;

import items.Item;
import util.RoomContainersState;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class RoomContainer implements Serializable {
    private static final long serialVersionUID = -5028607639889514468L;
    protected List<Item> content;
    protected RoomContainersState state;
    transient protected static ResourceBundle nameBundle = null;

    public void open(List<Item> items){
        if (state != RoomContainersState.OPEN) {
            state = RoomContainersState.OPEN;
            items.addAll(content);
        }
    }

    public void close(List<Item> items){
        if (state != RoomContainersState.CLOSED) {
            state = RoomContainersState.CLOSED;
            for(int i = 0; i < content.size(); i++) {
                if (!items.contains(content.get(i))){
                    //noinspection SuspiciousListRemoveInLoop
                    content.remove(i);
                }
            }
            items.removeAll(content);
        }

    }
    public static RoomContainer getContainer(String inputContainer) {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles/RoomContainersNames");
        try {
            for(String searchingString : bundle.keySet()){
                if(bundle.getString(searchingString).equalsIgnoreCase(inputContainer)) {
                    Class<?> foundClass = Class.forName(RoomContainer.class.getPackage().getName() + "." + searchingString);
                    return (RoomContainer) foundClass.getConstructor().newInstance();
                }
            }
            return null;		//Item not found.
        }
        catch(Exception ex) {
            //Item not found.
            return null;
        }
    }

    public static boolean isContainer(String inputContainer) {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles/RoomContainersNames");
        try {
            for(String searchingString : bundle.keySet()){
                if(bundle.getString(searchingString).equalsIgnoreCase(inputContainer)) {
                    return true;
                }
            }
            return false;		//Not a container.
        } catch(Exception ex) {
            //Not a container.
            return false;
        }
    }

    public boolean equals(RoomContainer comparedItem) {
        return this.getClass().equals(comparedItem.getClass());
    }

    public String getName() {
        return nameBundle.getString(this.getClass().getSimpleName());
    }

    public static void setNameBundle(Locale currentLocale) {
        nameBundle = ResourceBundle.getBundle("bundles/RoomContainersNames", currentLocale);
    }

    public RoomContainersState getState() {
        return state;
    }
}
