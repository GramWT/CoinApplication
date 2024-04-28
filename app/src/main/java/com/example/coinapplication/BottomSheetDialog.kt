package com.example.coinapplication

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.core.view.ViewCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import org.jetbrains.annotations.NotNull

open class BottomSheetDialog:BottomSheetDialogFragment() {

    private var behavior: BottomSheetBehavior<View>? = null

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as? Activity)?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val offsetFromTop = getWindowHeight() / 7
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            isFitToContents = false
            expandedOffset = offsetFromTop
        }
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog2
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window
            ?.decorView
            ?.findViewById<View>(com.google.android.material.R.id.touch_outside)
            ?.setOnClickListener(null)
    }

    @NotNull
    private fun createMaterialShapeDrawable(bottomSheet: View): MaterialShapeDrawable? {
        val shapeAppearanceModel =
            //Create a ShapeAppearanceModel with the same shapeAppearanceOverlay used in the style
            ShapeAppearanceModel.builder(context, 0, R.style.CustomShapeAppearanceBottomSheetDialog)
                .build()

        //Create a new MaterialShapeDrawable (you can't use the original MaterialShapeDrawable in the BottoSheet)
        val currentMaterialShapeDrawable = bottomSheet.background as? MaterialShapeDrawable
        return MaterialShapeDrawable(shapeAppearanceModel).apply {
            //Copy the attributes in the new MaterialShapeDrawable
            initializeElevationOverlay(context)
            fillColor = currentMaterialShapeDrawable?.fillColor
            tintList = currentMaterialShapeDrawable?.tintList
            currentMaterialShapeDrawable?.elevation?.also {
                this.elevation = it
            }
            currentMaterialShapeDrawable?.strokeWidth?.also {
                this.strokeWidth = it
            }
            currentMaterialShapeDrawable?.strokeColor?.also {
                this.strokeColor = it
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        dialog.setOnShowListener {
            (it as? BottomSheetDialog)?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                ?.also { designBottomSheet ->
                    behavior = BottomSheetBehavior.from(designBottomSheet)
                }

            behavior?.state = BottomSheetBehavior.STATE_EXPANDED
            behavior?.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behavior?.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        val newMaterialShapeDrawable = createMaterialShapeDrawable(bottomSheet)
                        ViewCompat.setBackground(bottomSheet, newMaterialShapeDrawable)
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }
            })
        }
        return dialog
    }

    protected fun getDialogHeight(): Int {
        return (getWindowHeight() / 7) * 6
    }
}