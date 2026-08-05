package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KeyMapping.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"platformDefaultKeyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "getPlatformDefaultKeyMapping", "()Landroidx/compose/foundation/text/KeyMapping;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KeyMapping_androidKt {
    private static final KeyMapping platformDefaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMapping_androidKt$platformDefaultKeyMapping$1
        @Override // androidx.compose.foundation.text.KeyMapping
        /* JADX INFO: renamed from: map-ZmokQxo */
        public KeyCommand mo774mapZmokQxo(KeyEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            KeyCommand keyCommand = null;
            if (KeyEvent_androidKt.m3974isShiftPressedZmokQxo(event) && KeyEvent_androidKt.m3971isAltPressedZmokQxo(event)) {
                long jM3968getKeyZmokQxo = KeyEvent_androidKt.m3968getKeyZmokQxo(event);
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m794getDirectionLeftEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_LINE_LEFT;
                } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m795getDirectionRightEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_LINE_RIGHT;
                } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m796getDirectionUpEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_HOME;
                } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m793getDirectionDownEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_END;
                }
            } else if (KeyEvent_androidKt.m3971isAltPressedZmokQxo(event)) {
                long jM3968getKeyZmokQxo2 = KeyEvent_androidKt.m3968getKeyZmokQxo(event);
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m794getDirectionLeftEK5gGoQ())) {
                    keyCommand = KeyCommand.LINE_LEFT;
                } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m795getDirectionRightEK5gGoQ())) {
                    keyCommand = KeyCommand.LINE_RIGHT;
                } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m796getDirectionUpEK5gGoQ())) {
                    keyCommand = KeyCommand.HOME;
                } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m793getDirectionDownEK5gGoQ())) {
                    keyCommand = KeyCommand.END;
                }
            }
            return keyCommand == null ? KeyMappingKt.getDefaultKeyMapping().mo774mapZmokQxo(event) : keyCommand;
        }
    };

    public static final KeyMapping getPlatformDefaultKeyMapping() {
        return platformDefaultKeyMapping;
    }
}
