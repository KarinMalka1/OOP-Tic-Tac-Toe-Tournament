/**
 * Factory for creating renderer instances.
 */
public class RendererFactory {

    /**
     * Constructs a renderer factory.
     */
    public RendererFactory() {

    }

    /**
     * Creates a renderer of the given type.
     * @param type renderer type
     * @param size board size
     * @return new renderer instance, or null if invalid
     */
    public Renderer buildRenderer(String type, int size) {
        switch (type) {
            case "console":
                return new ConsoleRenderer(size);
            case "void":
                return new VoidRenderer();
            default:
                return null;
        }
    }
}

