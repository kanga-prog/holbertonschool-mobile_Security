package androidx.compose.material3;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.core.os.ConfigurationCompat;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: Strings.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a1\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\bH\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"getString", "", "string", "Landroidx/compose/material3/Strings;", "getString-NWtq2-8", "(ILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "formatArgs", "", "", "getString-iSCLEhQ", "(I[Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Strings_androidKt {
    /* JADX INFO: renamed from: getString-NWtq2-8, reason: not valid java name */
    public static final String m1797getStringNWtq28(int string, Composer $composer, int $changed) {
        String string2;
        ComposerKt.sourceInformationMarkerStart($composer, -176762646, "C(getString)P(0:c#material3.Strings)29@1061L7,30@1102L7:Strings.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-176762646, $changed, -1, "androidx.compose.material3.getString (Strings.android.kt:28)");
        }
        ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        $composer.consume(localConfiguration);
        ComposerKt.sourceInformationMarkerEnd($composer);
        CompositionLocal this_$iv = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(this_$iv);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Resources resources = ((Context) objConsume).getResources();
        if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1777getNavigationMenuadMyvUU())) {
            string2 = resources.getString(androidx.compose.ui.R.string.navigation_menu);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.navigation_menu)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1739getCloseDraweradMyvUU())) {
            string2 = resources.getString(androidx.compose.ui.R.string.close_drawer);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.close_drawer)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1740getCloseSheetadMyvUU())) {
            string2 = resources.getString(androidx.compose.ui.R.string.close_sheet);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.close_sheet)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1772getDefaultErrorMessageadMyvUU())) {
            string2 = resources.getString(androidx.compose.ui.R.string.default_error_message);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…ng.default_error_message)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1774getExposedDropdownMenuadMyvUU())) {
            string2 = resources.getString(androidx.compose.ui.R.string.dropdown_menu);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.dropdown_menu)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1780getSliderRangeStartadMyvUU())) {
            string2 = resources.getString(androidx.compose.ui.R.string.range_start);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.range_start)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1779getSliderRangeEndadMyvUU())) {
            string2 = resources.getString(androidx.compose.ui.R.string.range_end);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.range_end)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1773getDialogadMyvUU())) {
            string2 = resources.getString(R.string.dialog);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(andr…aterial3.R.string.dialog)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1776getMenuExpandedadMyvUU())) {
            string2 = resources.getString(R.string.expanded);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(andr…erial3.R.string.expanded)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1775getMenuCollapsedadMyvUU())) {
            string2 = resources.getString(R.string.collapsed);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(andr…rial3.R.string.collapsed)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1781getSnackbarDismissadMyvUU())) {
            string2 = resources.getString(R.string.snackbar_dismiss);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …nackbar_dismiss\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1778getSearchBarSearchadMyvUU())) {
            string2 = resources.getString(R.string.search_bar_search);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …arch_bar_search\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1782getSuggestionsAvailableadMyvUU())) {
            string2 = resources.getString(R.string.suggestions_available);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(andr…ng.suggestions_available)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1761getDatePickerTitleadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_title);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …te_picker_title\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1749getDatePickerHeadlineadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_headline);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …picker_headline\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1763getDatePickerYearPickerPaneTitleadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_year_picker_pane_title);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …cker_pane_title\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1760getDatePickerSwitchToYearSelectionadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_switch_to_year_selection);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …_year_selection\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1756getDatePickerSwitchToDaySelectionadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_switch_to_day_selection);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …o_day_selection\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1758getDatePickerSwitchToNextMonthadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_switch_to_next_month);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …h_to_next_month\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1759getDatePickerSwitchToPreviousMonthadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_switch_to_previous_month);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …_previous_month\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1751getDatePickerNavigateToYearDescriptionadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_navigate_to_year_description);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …ear_description\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1750getDatePickerHeadlineDescriptionadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_headline_description);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …ine_description\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1752getDatePickerNoSelectionDescriptionadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_no_selection_description);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …ion_description\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1762getDatePickerTodayDescriptionadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_today_description);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …day_description\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1754getDatePickerScrollToShowLaterYearsadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_scroll_to_later_years);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …_to_later_years\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1753getDatePickerScrollToShowEarlierYearsadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_scroll_to_earlier_years);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …o_earlier_years\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1748getDateInputTitleadMyvUU())) {
            string2 = resources.getString(R.string.date_input_title);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …ate_input_title\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1741getDateInputHeadlineadMyvUU())) {
            string2 = resources.getString(R.string.date_input_headline);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …_input_headline\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1746getDateInputLabeladMyvUU())) {
            string2 = resources.getString(R.string.date_input_label);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …ate_input_label\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1742getDateInputHeadlineDescriptionadMyvUU())) {
            string2 = resources.getString(R.string.date_input_headline_description);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …ine_description\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1747getDateInputNoInputDescriptionadMyvUU())) {
            string2 = resources.getString(R.string.date_input_no_input_description);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …put_description\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1744getDateInputInvalidNotAllowedadMyvUU())) {
            string2 = resources.getString(R.string.date_input_invalid_not_allowed);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …lid_not_allowed\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1743getDateInputInvalidForPatternadMyvUU())) {
            string2 = resources.getString(R.string.date_input_invalid_for_pattern);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …lid_for_pattern\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1745getDateInputInvalidYearRangeadMyvUU())) {
            string2 = resources.getString(R.string.date_input_invalid_year_range);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …alid_year_range\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1755getDatePickerSwitchToCalendarModeadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_switch_to_calendar_mode);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …o_calendar_mode\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1757getDatePickerSwitchToInputModeadMyvUU())) {
            string2 = resources.getString(R.string.date_picker_switch_to_input_mode);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …h_to_input_mode\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1771getDateRangePickerTitleadMyvUU())) {
            string2 = resources.getString(R.string.date_range_picker_title);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …ge_picker_title\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1770getDateRangePickerStartHeadlineadMyvUU())) {
            string2 = resources.getString(R.string.date_range_picker_start_headline);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …_start_headline\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1767getDateRangePickerEndHeadlineadMyvUU())) {
            string2 = resources.getString(R.string.date_range_picker_end_headline);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …er_end_headline\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1768getDateRangePickerScrollToShowNextMonthadMyvUU())) {
            string2 = resources.getString(R.string.date_range_picker_scroll_to_next_month);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …l_to_next_month\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1769getDateRangePickerScrollToShowPreviousMonthadMyvUU())) {
            string2 = resources.getString(R.string.date_range_picker_scroll_to_previous_month);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …_previous_month\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1766getDateRangePickerDayInRangeadMyvUU())) {
            string2 = resources.getString(R.string.date_range_picker_day_in_range);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …er_day_in_range\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1765getDateRangeInputTitleadMyvUU())) {
            string2 = resources.getString(R.string.date_range_input_title);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …nge_input_title\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1764getDateRangeInputInvalidRangeInputadMyvUU())) {
            string2 = resources.getString(R.string.date_range_input_invalid_range_input);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …lid_range_input\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1737getBottomSheetPaneTitleadMyvUU())) {
            string2 = resources.getString(R.string.m3c_bottom_sheet_pane_title);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …heet_pane_title\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1735getBottomSheetDragHandleDescriptionadMyvUU())) {
            string2 = resources.getString(R.string.bottom_sheet_drag_handle_description);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …dle_description\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1738getBottomSheetPartialExpandDescriptionadMyvUU())) {
            string2 = resources.getString(R.string.bottom_sheet_collapse_description);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …pse_description\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1734getBottomSheetDismissDescriptionadMyvUU())) {
            string2 = resources.getString(R.string.bottom_sheet_dismiss_description);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …iss_description\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1736getBottomSheetExpandDescriptionadMyvUU())) {
            string2 = resources.getString(R.string.bottom_sheet_expand_description);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …and_description\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1795getTooltipLongPressLabeladMyvUU())) {
            string2 = resources.getString(R.string.tooltip_long_press_label);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …ong_press_label\n        )");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1784getTimePickerAMadMyvUU())) {
            string2 = resources.getString(R.string.time_picker_am);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   ….R.string.time_picker_am)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1793getTimePickerPMadMyvUU())) {
            string2 = resources.getString(R.string.time_picker_pm);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   ….R.string.time_picker_pm)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1794getTimePickerPeriodToggleadMyvUU())) {
            string2 = resources.getString(R.string.time_picker_period_toggle_description);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …eriod_toggle_description)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1790getTimePickerMinuteSelectionadMyvUU())) {
            string2 = resources.getString(R.string.time_picker_minute_selection);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …_picker_minute_selection)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1786getTimePickerHourSelectionadMyvUU())) {
            string2 = resources.getString(R.string.time_picker_hour_selection);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …me_picker_hour_selection)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1787getTimePickerHourSuffixadMyvUU())) {
            string2 = resources.getString(R.string.time_picker_hour_suffix);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   ….time_picker_hour_suffix)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1791getTimePickerMinuteSuffixadMyvUU())) {
            string2 = resources.getString(R.string.time_picker_minute_suffix);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …ime_picker_minute_suffix)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1783getTimePicker24HourSuffixadMyvUU())) {
            string2 = resources.getString(R.string.time_picker_hour_24h_suffix);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …e_picker_hour_24h_suffix)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1785getTimePickerHouradMyvUU())) {
            string2 = resources.getString(R.string.time_picker_hour);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   ….string.time_picker_hour)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1789getTimePickerMinuteadMyvUU())) {
            string2 = resources.getString(R.string.time_picker_minute);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …tring.time_picker_minute)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1788getTimePickerHourTextFieldadMyvUU())) {
            string2 = resources.getString(R.string.time_picker_hour_text_field);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …e_picker_hour_text_field)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1792getTimePickerMinuteTextFieldadMyvUU())) {
            string2 = resources.getString(R.string.time_picker_minute_text_field);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …picker_minute_text_field)");
        } else if (Strings.m1730equalsimpl0(string, Strings.INSTANCE.m1796getTooltipPaneDescriptionadMyvUU())) {
            string2 = resources.getString(R.string.tooltip_pane_description);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …tooltip_pane_description)");
        } else {
            string2 = "";
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return string2;
    }

    /* JADX INFO: renamed from: getString-iSCLEhQ, reason: not valid java name */
    public static final String m1798getStringiSCLEhQ(int string, Object[] formatArgs, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(formatArgs, "formatArgs");
        ComposerKt.sourceInformationMarkerStart($composer, -1126124681, "C(getString)P(1:c#material3.Strings)205@10113L17,207@10206L7:Strings.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1126124681, $changed, -1, "androidx.compose.material3.getString (Strings.android.kt:204)");
        }
        String raw = m1797getStringNWtq28(string, $composer, $changed & 14);
        ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localConfiguration);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Locale locale = ConfigurationCompat.getLocales((Configuration) objConsume).get(0);
        if (locale == null) {
            locale = Locale.getDefault();
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArrCopyOf = Arrays.copyOf(formatArgs, formatArgs.length);
        String str = String.format(locale, raw, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return str;
    }
}
