/**
 * A factory class that creates {@link Renderer} objects according to a given type and board size.
 * <p>
 * This class follows the <b>Factory Design Pattern</b>, encapsulating the logic of object creation.
 * It supports generating multiple renderer types based on user input while maintaining unified API.
 * </p>
 *
 * <p>Supported renderer types:</p>
 * <ul>
 *     <li>{@value #CONSOLE_TYPE} – creates a {@link ConsoleRenderer} that displays the board on screen.</li>
 *     <li>{@value #VOID_TYPE} – creates a {@link VoidRenderer}
 *     that performs no rendering (used for simulations).</li>
 * </ul>
 *
 * <p>This design demonstrates <b>Abstraction</b> and <b>Encapsulation</b>:
 * the caller does not need to know how each renderer is instantiated.</p>
 *
 * @author Naama Even-Oz
 * @see Renderer
 * @see ConsoleRenderer
 * @see VoidRenderer
 */
public class RendererFactory {

    /** Identifier for creating a console renderer. */
    private static final String CONSOLE_TYPE = "console";

    /** Identifier for creating a void renderer. */
    private static final String VOID_TYPE = "void";

    /** Default constructor for RendererFactory. */
    public RendererFactory() {}

    /**
     * Builds and returns a renderer object according to the specified type and size.
     * <p>
     * If the type is {@value #CONSOLE_TYPE}, a {@link ConsoleRenderer} of the given size is created.
     * If the type is {@value #VOID_TYPE}, a {@link VoidRenderer} is created.
     * If the type is invalid, the method returns {@code null}.
     * </p>
     *
     * @param type the renderer type identifier ({@value #CONSOLE_TYPE} or {@value #VOID_TYPE}).
     * @param size the board size used when creating a console renderer.
     * @return a {@link Renderer} instance matching the given type, or {@code null} if invalid.
     */
    public Renderer buildRenderer(String type, int size) {
        if (type == null) {
            return null;
        }

        // Normalize string for case-insensitive comparison
        String normalizedType = type.toLowerCase();

        // Create renderer according to type using switch
        switch (normalizedType) {
            case CONSOLE_TYPE:
                return new ConsoleRenderer(size);

            case VOID_TYPE:
                return new VoidRenderer();

            default:
                return null;
        }
    }
}
