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
package com.txusballesteros.brewerydb.view.search

import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.di.ViewComponent

class SearchFragment: AbsFragment() {
  companion object {
    fun newInstance(): SearchFragment
      = SearchFragment()
  }

  override fun onRequestLayoutResourceId(): Int
    = R.layout.fragment_search

  override fun onPresenterShouldBeAttached() { }

  override fun onPresenterShouldBeDetached() { }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }
}