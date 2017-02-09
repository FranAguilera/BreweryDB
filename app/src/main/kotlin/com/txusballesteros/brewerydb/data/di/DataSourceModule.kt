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
package com.txusballesteros.brewerydb.data.di

import com.txusballesteros.brewerydb.data.styles.datasource.StylesCloudDataSource
import com.txusballesteros.brewerydb.data.styles.datasource.StylesCloudDataSourceImpl
import com.txusballesteros.brewerydb.data.styles.datasource.StylesInMemoryLocalDataSource
import com.txusballesteros.brewerydb.data.styles.datasource.StylesLocalDataSource
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {
  @Provides
  fun provideStylesLocalDataSource(dataSource: StylesInMemoryLocalDataSource) : StylesLocalDataSource {
      return dataSource
  }

  @Provides
  fun provideStylesCloudDataSource(dataSource: StylesCloudDataSourceImpl) : StylesCloudDataSource {
    return dataSource
  }
}