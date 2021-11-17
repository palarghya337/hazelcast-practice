package in.java.practice.hazelcast.listeners;

import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;

public class CustomListener implements LifecycleListener {
    @Override
    public void stateChanged(LifecycleEvent lifecycleEvent) {
        if (LifecycleEvent.LifecycleState.CLIENT_CONNECTED.equals(lifecycleEvent.getState())) {

        }
    }
}
