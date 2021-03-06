/*
 * Copyright Txus Ballesteros 2017 (@txusballesteros)
 *
 * This file is part of Foobar.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.brewerydb.view.beers

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.data.model.BeerIngredientViewModel
import com.txusballesteros.brewerydb.extension.setEmptyView
import com.txusballesteros.brewerydb.presentation.beers.BeerIngredientsPresenter
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.ErrorBehaviour
import com.txusballesteros.brewerydb.view.behaviours.LoadingBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_beer_ingredients.*
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class BeerIngredientsFragment: AbsFragment(), BeerIngredientsPresenter.View {
  companion object {
    val EXTRA_BEER_ID = "extra:beerId"

    fun newInstance(beerId: String): BeerIngredientsFragment {
      return BeerIngredientsFragment().withArguments(
          EXTRA_BEER_ID to beerId
      )
    }
  }

  @Inject lateinit var loadingBehaviour: LoadingBehaviour
  @Inject lateinit var errorBehaviour: ErrorBehaviour
  @Inject lateinit var presenter: BeerIngredientsPresenter
  lateinit var adapter: BeerIngredientsAdapter

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_beer_ingredients
  }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }

  override fun onPresenterShouldBeAttached() {
    presenter.onAttachView(this)
  }

  override fun onPresenterShouldBeDetached() {
    presenter.onDetachView()
  }

  override fun onRequestViewBehaviours() {
    loadingBehaviour.inject(activity)
    errorBehaviour.inject(activity, {
      requestIngredients()
    })
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    initializeList()
    requestIngredients()
  }

  private fun requestIngredients() {
    val beerId = getBeerId()
    presenter.onRequestIngredients(beerId)
  }

  private fun initializeList() {
    adapter = BeerIngredientsAdapter({
      presenter.onIngredientClick(it)
    })
    list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    list.setHasFixedSize(true)
    list.adapter = adapter
    list.setEmptyView(emptyView)
  }

  override fun showLoading() {
    errorBehaviour.hideError()
    loadingBehaviour.showLoading()
  }

  override fun hideLoading() {
    loadingBehaviour.hideLoading()
  }

  override fun renderIngredients(ingredients: List<BeerIngredientViewModel>) {
    adapter.clear()
    adapter.addAll(ingredients)
    adapter.notifyDataSetChanged()
  }

  override fun renderError() {
    errorBehaviour.showError()
  }

  private fun getBeerId(): String {
    return arguments.getString(EXTRA_BEER_ID)
  }
}