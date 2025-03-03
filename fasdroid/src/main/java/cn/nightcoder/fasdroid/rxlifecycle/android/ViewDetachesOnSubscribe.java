package cn.nightcoder.fasdroid.rxlifecycle.android;

import android.view.View;

import cn.nightcoder.fasdroid.rxandroid.MainThreadDisposable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

import static cn.nightcoder.fasdroid.rxandroid.MainThreadDisposable.verifyMainThread;

final class ViewDetachesOnSubscribe implements ObservableOnSubscribe<Object> {

    static final Object SIGNAL = new Object();

    final View view;

    public ViewDetachesOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
        verifyMainThread();
        EmitterListener listener = new EmitterListener(emitter);
        emitter.setDisposable(listener);
        view.addOnAttachStateChangeListener(listener);
    }

    class EmitterListener extends MainThreadDisposable implements View.OnAttachStateChangeListener {
        final ObservableEmitter<Object> emitter;

        public EmitterListener(ObservableEmitter<Object> emitter) {
            this.emitter = emitter;
        }

        @Override
        public void onViewAttachedToWindow(View view) {
            // Do nothing
        }

        @Override
        public void onViewDetachedFromWindow(View view) {
            emitter.onNext(SIGNAL);
        }

        @Override
        protected void onDispose() {
            view.removeOnAttachStateChangeListener(this);
        }
    }

}