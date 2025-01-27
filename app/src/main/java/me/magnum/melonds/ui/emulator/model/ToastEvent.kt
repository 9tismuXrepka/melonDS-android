package me.magnum.melonds.ui.emulator.model

sealed class ToastEvent {
    object GbaLoadFailed : ToastEvent()
    object ResetFailed : ToastEvent()
    object RewindNotEnabled : ToastEvent()
    object RewindNotAvailableWhileRAHardcoreModeEnabled : ToastEvent()
    object StateSaveFailed : ToastEvent()
    object StateLoadFailed : ToastEvent()
    object StateStateDoesNotExist : ToastEvent()
    object QuickSaveSuccessful : ToastEvent()
    object QuickLoadSuccessful : ToastEvent()
    object CannotUseSaveStatesWhenRAHardcoreIsEnabled : ToastEvent()
    object CannotSaveStateWhenRunningFirmware : ToastEvent()
    object CannotLoadStateWhenRunningFirmware : ToastEvent()
}