package com.example.advertpal.features.details

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.advertpal.App
import com.example.advertpal.data.models.works.Work
import com.example.advertpal.features.works.WorksViewModel
import com.example.advertpal.utils.USER_ID_EXTRA
import com.example.advertpal.utils.WORK_EXTRA
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject




class DetailsActivity : AppCompatActivity() {

    private lateinit var work: Work

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WorksViewModel

    private lateinit var snackbarDeleted: Snackbar

    private lateinit var userId : String

    private var isDeleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.advertpal.R.layout.activity_details)
        setSupportActionBar(inc_toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        App.component.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[WorksViewModel::class.java]

        work = intent.getSerializableExtra(WORK_EXTRA) as Work
        userId = intent.getStringExtra(USER_ID_EXTRA)
        title = work.group?.name

        DetailsViewHolder(this).bind(work)

        snackbarDeleted = Snackbar
            .make(findViewById(com.example.advertpal.R.id.activity_details), "Удалено", Snackbar.LENGTH_INDEFINITE)
            .setAction("Восстановить") {
                viewModel.addWork(work, userId)
                isDeleted = false
                hideSnackbar()
            }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.advertpal.R.menu.mnu_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            com.example.advertpal.R.id.action_delete -> {
                if (!isDeleted) {
                    snackbarDeleted.show()
                    isDeleted = true
                    viewModel.deleteWork(work.id)
                }
            }
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    private fun hideSnackbar() {
        snackbarDeleted.dismiss()
    }
}
