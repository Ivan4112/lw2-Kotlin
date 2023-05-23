package com.example.lw2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.vacancy_view_activity.cSharp
import kotlinx.android.synthetic.main.vacancy_view_activity.java

class ViewActivity: AppCompatActivity(), BaseDialog.Listener {
    private val viewModel = VacancyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vacancy_view_activity)
        viewModel.getVacancies()

        viewModel.vacancies.observe(this) {
            setTitlesAndActions(
                vacancies = it,
                java, cSharp
            )
        }
    }

    private fun setTitlesAndActions(vacancies: List<Vacancy>?, vararg vacancyView: VacancyView) {
        vacancyView.forEachIndexed { index, vacancyView ->
            val vacancy = vacancies?.getOrElse(index) { Vacancy("", "", "") }
            val title = vacancy!!.title
            val salary = vacancy.salary
            val company = vacancy.companyName

            vacancyView.setTitle(title, salary, company)
            vacancyView.setOnClickListener {
                "Шукають працівника: ${vacancy.title} з ЗП: ${vacancy.salary} в компанію: ${vacancy.companyName}"
                BaseDialog.newInstance(title, supportFragmentManager)
            }
        }

    }


    override fun onClick() {
        Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
    }
}