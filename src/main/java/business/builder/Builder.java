package business.builder;

/**
 * Generic builder interface for constructing objects of type {@code T}.
 * <p>
 * This interface follows the <b>Builder design pattern</b>, providing a
 * standardized contract for assembling complex objects step by step.
 * Implementations are responsible for collecting configuration or state
 * and producing a fully constructed instance via {@link #build()}.
 * </p>
 *
 * @param <T> the type of object this builder produces
 */
public interface Builder<T> {

    /**
     * Constructs and returns a fully built instance of type {@code T}.
     * <p>
     * All required parameters should be set on the builder before calling
     * this method. Implementations may throw an exception if mandatory
     * fields are missing or invalid.
     * </p>
     *
     * @return a new instance of {@code T} assembled from the builder's current state
     */
    T build();
}