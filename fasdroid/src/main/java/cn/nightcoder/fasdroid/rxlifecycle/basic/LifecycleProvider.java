package cn.nightcoder.fasdroid.rxlifecycle.basic;


import androidx.annotation.NonNull;

import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.core.Observable;

/**
 * Common base interface for activity and fragment lifecycle providers.
 * <p>
 * Useful if you are writing utilities on top of rxlifecycle-components
 * or implementing your own component not supported by this library.
 */
public interface LifecycleProvider<E> {
    /**
     * @return a sequence of lifecycle events
     */
    @NonNull
    @CheckReturnValue
    Observable<E> lifecycle();

    /**
     * Binds a source until a specific event occurs.
     *
     * @param event the event that triggers unsubscription
     * @return a reusable {@link LifecycleTransformer} which unsubscribes when the event triggers.
     */
    @NonNull
    @CheckReturnValue
    <T> LifecycleTransformer<T> bindUntilEvent(@NonNull E event);

    /**
     * Binds a source until the next reasonable event occurs.
     *
     * @return a reusable {@link LifecycleTransformer} which unsubscribes at the correct time.
     */
    @NonNull
    @CheckReturnValue
    <T> LifecycleTransformer<T> bindToLifecycle();
}