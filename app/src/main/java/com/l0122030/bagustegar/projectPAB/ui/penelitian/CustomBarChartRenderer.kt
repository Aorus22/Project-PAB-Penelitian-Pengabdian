package com.l0122030.bagustegar.projectPAB.ui.penelitian

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.utils.ViewPortHandler

class CustomBarChartRenderer(
    chart: BarDataProvider?,
    animator: ChartAnimator?,
    viewPortHandler: ViewPortHandler?,
    private val labels: List<String>
) : BarChartRenderer(chart, animator, viewPortHandler) {

    private val drawLabelPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 24f // Ukuran teks di dalam bar
        textAlign = Paint.Align.CENTER
    }

    override fun drawValue(c: Canvas, valueText: String?, x: Float, y: Float, color: Int) {
        c.drawText(valueText ?: "", x, y - 10, drawLabelPaint) // Menggambar teks di dalam bar
    }

    override fun drawValues(c: Canvas) {
        super.drawValues(c) // Tetap menggambar nilai standar
    }

    override fun drawHighlighted(c: Canvas?, indices: Array<out Highlight>?) {
        super.drawHighlighted(c, indices) // Tetap menggambar highlight standar
    }
}
