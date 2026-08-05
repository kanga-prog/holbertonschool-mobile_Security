package androidx.compose.material3;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DateInput.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r¢\u0006\u0002\u0010\u0011J-\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Landroidx/compose/material3/DateInputValidator;", "", "stateData", "Landroidx/compose/material3/StateData;", "dateInputFormat", "Landroidx/compose/material3/DateInputFormat;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "dateValidator", "Lkotlin/Function1;", "", "", "errorDatePattern", "", "errorDateOutOfYearRange", "errorInvalidNotAllowed", "errorInvalidRangeInput", "(Landroidx/compose/material3/StateData;Landroidx/compose/material3/DateInputFormat;Landroidx/compose/material3/DatePickerFormatter;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "validate", "calendarDate", "Landroidx/compose/material3/CalendarDate;", "inputIdentifier", "Landroidx/compose/material3/InputIdentifier;", "locale", "Ljava/util/Locale;", "validate-XivgLIo", "(Landroidx/compose/material3/CalendarDate;ILjava/util/Locale;)Ljava/lang/String;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DateInputValidator {
    private final DatePickerFormatter dateFormatter;
    private final DateInputFormat dateInputFormat;
    private final Function1<Long, Boolean> dateValidator;
    private final String errorDateOutOfYearRange;
    private final String errorDatePattern;
    private final String errorInvalidNotAllowed;
    private final String errorInvalidRangeInput;
    private final StateData stateData;

    /* JADX WARN: Multi-variable type inference failed */
    public DateInputValidator(StateData stateData, DateInputFormat dateInputFormat, DatePickerFormatter dateFormatter, Function1<? super Long, Boolean> dateValidator, String errorDatePattern, String errorDateOutOfYearRange, String errorInvalidNotAllowed, String errorInvalidRangeInput) {
        Intrinsics.checkNotNullParameter(stateData, "stateData");
        Intrinsics.checkNotNullParameter(dateInputFormat, "dateInputFormat");
        Intrinsics.checkNotNullParameter(dateFormatter, "dateFormatter");
        Intrinsics.checkNotNullParameter(dateValidator, "dateValidator");
        Intrinsics.checkNotNullParameter(errorDatePattern, "errorDatePattern");
        Intrinsics.checkNotNullParameter(errorDateOutOfYearRange, "errorDateOutOfYearRange");
        Intrinsics.checkNotNullParameter(errorInvalidNotAllowed, "errorInvalidNotAllowed");
        Intrinsics.checkNotNullParameter(errorInvalidRangeInput, "errorInvalidRangeInput");
        this.stateData = stateData;
        this.dateInputFormat = dateInputFormat;
        this.dateFormatter = dateFormatter;
        this.dateValidator = dateValidator;
        this.errorDatePattern = errorDatePattern;
        this.errorDateOutOfYearRange = errorDateOutOfYearRange;
        this.errorInvalidNotAllowed = errorInvalidNotAllowed;
        this.errorInvalidRangeInput = errorInvalidRangeInput;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x00da  */
    /* JADX WARN: Code duplicated, block: B:24:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:26:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:27:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:32:0x0106 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:34:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: validate-XivgLIo, reason: not valid java name */
    public final String m1465validateXivgLIo(CalendarDate calendarDate, int inputIdentifier, Locale locale) {
        long utcTimeMillis;
        CalendarDate value;
        long utcTimeMillis2;
        Intrinsics.checkNotNullParameter(locale, "locale");
        if (calendarDate == null) {
            String str = this.errorDatePattern;
            String upperCase = this.dateInputFormat.getPatternWithDelimiters().toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String str2 = String.format(str, Arrays.copyOf(new Object[]{upperCase}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(this, *args)");
            return str2;
        }
        if (!this.stateData.getYearRange().contains(calendarDate.getYear())) {
            String str3 = String.format(this.errorDateOutOfYearRange, Arrays.copyOf(new Object[]{DatePickerKt.toLocalString(this.stateData.getYearRange().getFirst()), DatePickerKt.toLocalString(this.stateData.getYearRange().getLast())}, 2));
            Intrinsics.checkNotNullExpressionValue(str3, "format(this, *args)");
            return str3;
        }
        if (!this.dateValidator.invoke(Long.valueOf(calendarDate.getUtcTimeMillis())).booleanValue()) {
            String str4 = String.format(this.errorInvalidNotAllowed, Arrays.copyOf(new Object[]{DatePickerFormatter.formatDate$material3_release$default(this.dateFormatter, calendarDate, this.stateData.getCalendarModel(), locale, false, 8, null)}, 1));
            Intrinsics.checkNotNullExpressionValue(str4, "format(this, *args)");
            return str4;
        }
        if (InputIdentifier.m1579equalsimpl0(inputIdentifier, InputIdentifier.INSTANCE.m1585getStartDateInputJ2x2o4M())) {
            long utcTimeMillis3 = calendarDate.getUtcTimeMillis();
            CalendarDate value2 = this.stateData.getSelectedEndDate().getValue();
            if (utcTimeMillis3 < (value2 != null ? value2.getUtcTimeMillis() : Long.MAX_VALUE)) {
                if (InputIdentifier.m1579equalsimpl0(inputIdentifier, InputIdentifier.INSTANCE.m1583getEndDateInputJ2x2o4M())) {
                    utcTimeMillis = calendarDate.getUtcTimeMillis();
                    value = this.stateData.getSelectedStartDate().getValue();
                    if (value != null) {
                        utcTimeMillis2 = value.getUtcTimeMillis();
                    } else {
                        utcTimeMillis2 = Long.MIN_VALUE;
                    }
                    if (utcTimeMillis > utcTimeMillis2) {
                        return "";
                    }
                } else {
                    return "";
                }
            }
        } else if (InputIdentifier.m1579equalsimpl0(inputIdentifier, InputIdentifier.INSTANCE.m1583getEndDateInputJ2x2o4M())) {
            utcTimeMillis = calendarDate.getUtcTimeMillis();
            value = this.stateData.getSelectedStartDate().getValue();
            if (value != null) {
                utcTimeMillis2 = value.getUtcTimeMillis();
            } else {
                utcTimeMillis2 = Long.MIN_VALUE;
            }
            if (utcTimeMillis > utcTimeMillis2) {
                return "";
            }
        } else {
            return "";
        }
        return this.errorInvalidRangeInput;
    }
}
