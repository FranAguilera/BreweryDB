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
package com.txusballesteros.brewerydb.domain.reactive

import com.txusballesteros.brewerydb.threading.MainThreadExecutor

class Subject(private val executor: MainThreadExecutor) {
  private val observers: MutableList<Observer> = ArrayList()

  fun subscribe(observer: Observer) {
    observers.add(observer)
  }

  fun unsubscribe(observer: Observer) {
    observers.remove(observer)
  }

  fun hasObservers(): Boolean {
    return !observers.isEmpty()
  }

  fun asObserver(): Observer {
    return Observer(this)
  }

  fun onNext() {
    if (hasObservers()) {
      executor.execute(Runnable {
        observers.forEach { it.onNext() }
      })
    }
  }
}