package com.example.lw2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VacancyViewModel: ViewModel() {
    val vacancies = MutableLiveData<List<Vacancy>>()

    fun getVacancies(){
        vacancies.value = listOf(
            Vacancy("JavaDev", "1000", "Sigma"),
            Vacancy("C#", "900", "Epam"),
        )
    }
}