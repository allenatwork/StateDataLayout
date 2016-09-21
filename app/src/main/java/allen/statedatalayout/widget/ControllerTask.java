package allen.statedatalayout.widget;

/**
 * Created by Allen on 20-Sep-16.
 */
public interface ControllerTask<K> {
    void reload();

    void displayData(K data);
}
