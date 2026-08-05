package androidx.compose.foundation.text;

import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;

/* JADX INFO: compiled from: KeyMapping.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u001a\u001f\u0010\u0004\u001a\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0000ø\u0001\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"defaultKeyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "getDefaultKeyMapping", "()Landroidx/compose/foundation/text/KeyMapping;", "commonKeyMapping", "shortcutModifier", "Lkotlin/Function1;", "Landroidx/compose/ui/input/key/KeyEvent;", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KeyMappingKt {
    private static final KeyMapping defaultKeyMapping;

    public static final KeyMapping commonKeyMapping(final Function1<? super KeyEvent, Boolean> shortcutModifier) {
        Intrinsics.checkNotNullParameter(shortcutModifier, "shortcutModifier");
        return new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt.commonKeyMapping.1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo774mapZmokQxo(android.view.KeyEvent event) {
                Intrinsics.checkNotNullParameter(event, "event");
                if (shortcutModifier.invoke(KeyEvent.m3951boximpl(event)).booleanValue() && KeyEvent_androidKt.m3974isShiftPressedZmokQxo(event)) {
                    if (Key.m3660equalsimpl0(KeyEvent_androidKt.m3968getKeyZmokQxo(event), MappedKeys.INSTANCE.m809getZEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    return null;
                }
                if (shortcutModifier.invoke(KeyEvent.m3951boximpl(event)).booleanValue()) {
                    long jM3968getKeyZmokQxo = KeyEvent_androidKt.m3968getKeyZmokQxo(event);
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m789getCEK5gGoQ()) ? true : Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m799getInsertEK5gGoQ())) {
                        return KeyCommand.COPY;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m806getVEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m807getXEK5gGoQ())) {
                        return KeyCommand.CUT;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m786getAEK5gGoQ())) {
                        return KeyCommand.SELECT_ALL;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m808getYEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m809getZEK5gGoQ())) {
                        return KeyCommand.UNDO;
                    }
                    return null;
                }
                if (KeyEvent_androidKt.m3972isCtrlPressedZmokQxo(event)) {
                    return null;
                }
                if (KeyEvent_androidKt.m3974isShiftPressedZmokQxo(event)) {
                    long jM3968getKeyZmokQxo2 = KeyEvent_androidKt.m3968getKeyZmokQxo(event);
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m794getDirectionLeftEK5gGoQ())) {
                        return KeyCommand.SELECT_LEFT_CHAR;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m795getDirectionRightEK5gGoQ())) {
                        return KeyCommand.SELECT_RIGHT_CHAR;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m796getDirectionUpEK5gGoQ())) {
                        return KeyCommand.SELECT_UP;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m793getDirectionDownEK5gGoQ())) {
                        return KeyCommand.SELECT_DOWN;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m803getPageUpEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_UP;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m802getPageDownEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_DOWN;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m801getMoveHomeEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_START;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m800getMoveEndEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_END;
                    }
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m799getInsertEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    return null;
                }
                long jM3968getKeyZmokQxo3 = KeyEvent_androidKt.m3968getKeyZmokQxo(event);
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m794getDirectionLeftEK5gGoQ())) {
                    return KeyCommand.LEFT_CHAR;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m795getDirectionRightEK5gGoQ())) {
                    return KeyCommand.RIGHT_CHAR;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m796getDirectionUpEK5gGoQ())) {
                    return KeyCommand.UP;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m793getDirectionDownEK5gGoQ())) {
                    return KeyCommand.DOWN;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m803getPageUpEK5gGoQ())) {
                    return KeyCommand.PAGE_UP;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m802getPageDownEK5gGoQ())) {
                    return KeyCommand.PAGE_DOWN;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m801getMoveHomeEK5gGoQ())) {
                    return KeyCommand.LINE_START;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m800getMoveEndEK5gGoQ())) {
                    return KeyCommand.LINE_END;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m797getEnterEK5gGoQ())) {
                    return KeyCommand.NEW_LINE;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m788getBackspaceEK5gGoQ())) {
                    return KeyCommand.DELETE_PREV_CHAR;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m792getDeleteEK5gGoQ())) {
                    return KeyCommand.DELETE_NEXT_CHAR;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m804getPasteEK5gGoQ())) {
                    return KeyCommand.PASTE;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m791getCutEK5gGoQ())) {
                    return KeyCommand.CUT;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m790getCopyEK5gGoQ())) {
                    return KeyCommand.COPY;
                }
                if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m805getTabEK5gGoQ())) {
                    return KeyCommand.TAB;
                }
                return null;
            }
        };
    }

    public static final KeyMapping getDefaultKeyMapping() {
        return defaultKeyMapping;
    }

    static {
        final KeyMapping common = commonKeyMapping(new PropertyReference1Impl() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object receiver0) {
                return Boolean.valueOf(KeyEvent_androidKt.m3972isCtrlPressedZmokQxo(((KeyEvent) receiver0).m3957unboximpl()));
            }
        });
        defaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$2$1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo774mapZmokQxo(android.view.KeyEvent event) {
                Intrinsics.checkNotNullParameter(event, "event");
                KeyCommand keyCommand = null;
                if (KeyEvent_androidKt.m3974isShiftPressedZmokQxo(event) && KeyEvent_androidKt.m3972isCtrlPressedZmokQxo(event)) {
                    long jM3968getKeyZmokQxo = KeyEvent_androidKt.m3968getKeyZmokQxo(event);
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m794getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LEFT_WORD;
                    } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m795getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_RIGHT_WORD;
                    } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m796getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_PREV_PARAGRAPH;
                    } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo, MappedKeys.INSTANCE.m793getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_NEXT_PARAGRAPH;
                    }
                } else if (KeyEvent_androidKt.m3972isCtrlPressedZmokQxo(event)) {
                    long jM3968getKeyZmokQxo2 = KeyEvent_androidKt.m3968getKeyZmokQxo(event);
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m794getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.LEFT_WORD;
                    } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m795getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.RIGHT_WORD;
                    } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m796getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.PREV_PARAGRAPH;
                    } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m793getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.NEXT_PARAGRAPH;
                    } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m798getHEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_CHAR;
                    } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m792getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_NEXT_WORD;
                    } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m788getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_WORD;
                    } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo2, MappedKeys.INSTANCE.m787getBackslashEK5gGoQ())) {
                        keyCommand = KeyCommand.DESELECT;
                    }
                } else if (KeyEvent_androidKt.m3974isShiftPressedZmokQxo(event)) {
                    long jM3968getKeyZmokQxo3 = KeyEvent_androidKt.m3968getKeyZmokQxo(event);
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m801getMoveHomeEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_LEFT;
                    } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo3, MappedKeys.INSTANCE.m800getMoveEndEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_RIGHT;
                    }
                } else if (KeyEvent_androidKt.m3971isAltPressedZmokQxo(event)) {
                    long jM3968getKeyZmokQxo4 = KeyEvent_androidKt.m3968getKeyZmokQxo(event);
                    if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo4, MappedKeys.INSTANCE.m788getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_FROM_LINE_START;
                    } else if (Key.m3660equalsimpl0(jM3968getKeyZmokQxo4, MappedKeys.INSTANCE.m792getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_TO_LINE_END;
                    }
                }
                if (keyCommand != null) {
                    return keyCommand;
                }
                return common.mo774mapZmokQxo(event);
            }
        };
    }
}
