package cn.nightcoder.fasdroid.rxandroid;

import android.os.Looper;

import cn.nightcoder.fasdroid.rxandroid.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A {@linkplain Disposable disposable} which ensures its {@linkplain #onDispose()
 * dispose action} is executed on the main thread. When unsubscription occurs on a different
 * thread than the main thread, the action is posted to run on the main thread as soon as possible.
 * <p>
 * Instances of this class are useful in creating observables which interact with APIs that can
 * only be used on the main thread, such as UI objects.
 * <p>
 * A {@link #verifyMainThread() convenience method} is also provided for validating whether code
 * is being called on the main thread. Calls to this method along with instances of this class are
 * commonly used when creating custom observables using the following pattern:
 * <pre><code>
 * &#064;Override public void subscribe(Observer<? extends T> o) {
 *   MainThreadDisposable.verifyMainThread();
 *
 *   // TODO setup behavior
 *
 *    o.onSubscribe(new MainThreadDisposable() {
 *     &#064;Override protected void onDispose() {
 *       // TODO undo behavior
 *     }
 *   });
 * }
 * </code></pre>
 */
public abstract class MainThreadDisposable implements Disposable {
    /**
     * Verify that the calling thread is the Android main thread.
     * <p>
     * Calls to this method are usually preconditions for subscription behavior which instances of
     * this class later undo. See the class documentation for an example.
     *
     * @throws IllegalStateException when called from any other thread.
     */
    public static void verifyMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException(
                    "Expected to be called on the main thread but was " + Thread.currentThread().getName());
        }
    }

    private final AtomicBoolean unsubscribed = new AtomicBoolean();

    @Override
    public final boolean isDisposed() {
        return unsubscribed.get();
    }

    @Override
    public final void dispose() {
        if (unsubscribed.compareAndSet(false, true)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                onDispose();
            } else {
                AndroidSchedulers.mainThread().scheduleDirect(new Runnable() {
                    @Override public void run() {
                        onDispose();
                    }
                });
            }
        }
    }

    protected abstract void onDispose();
}
