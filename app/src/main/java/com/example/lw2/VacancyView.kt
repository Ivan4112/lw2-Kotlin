package com.example.lw2

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import kotlinx.android.synthetic.main.vacancy_view.view.checkBox
import kotlinx.android.synthetic.main.vacancy_view.view.company
import kotlinx.android.synthetic.main.vacancy_view.view.root
import kotlinx.android.synthetic.main.vacancy_view.view.salary
import kotlinx.android.synthetic.main.vacancy_view.view.title
import kotlinx.android.synthetic.main.vacancy_view.view.vacancyImage

class VacancyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var selectedColor = -1
    private var unSelectedColor = -1

    init {
        inflate(context, R.layout.vacancy_view, this)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.VacancyView
        ).use {
            selectedColor = it.getColor(R.styleable.VacancyView_selectedColor, -1)
            unSelectedColor = it.getColor(R.styleable.VacancyView_unSelectedColor, -1)
            title.text= it.getText(R.styleable.VacancyView_title)
            salary.text = it.getText(R.styleable.VacancyView_salary)
            company.text = it.getText(R.styleable.VacancyView_company)
            vacancyImage.setImageDrawable(it.getDrawable(R.styleable.VacancyView_imgLog))
            root.setBackground(unSelectedColor)
        }
        setOnClickListener()
    }

    fun setTitle(myTitle: String, mySalary: String, myCompany: String) {
        title.text = myTitle
        salary.text = mySalary
        company.text = myCompany
    }

    private fun setOnClickListener() {
//        setOnCheckedChangeListener { _, isChecked ->
        vacancyImage.setOnClickListener {
            val color = if (isClickable) selectedColor else unSelectedColor
            root.setBackground(color)
        }
    }

    private fun View.setBackground(@ColorInt color: Int) =
        (background as GradientDrawable).apply {
            cornerRadius = resources.getDimension(R.dimen.dp20)
            setStroke(resources.getDimensionPixelSize(R.dimen.dp2), color)
        }
}