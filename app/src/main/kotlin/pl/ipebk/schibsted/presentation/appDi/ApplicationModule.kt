package pl.ipebk.schibsted.presentation.appDi

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import android.view.LayoutInflater
import com.github.bskierys.pine.Pine
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import pl.ipebk.schibsted.cache.RealmProvider
import pl.ipebk.schibsted.presentation.App
import timber.log.Timber
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

  init {
    Realm.init(app)
    val config = RealmConfiguration.Builder().name("recipes.realm").build()
    Realm.setDefaultConfiguration(config)
  }

  @Provides
  @Singleton
  fun provideApplication(): Application = app

  @Provides
  @Singleton
  @ApplicationScope
  fun provideContext(): Context = app.baseContext

  @Provides
  @Singleton
  fun provideLayoutInflater(@ApplicationScope context: Context): LayoutInflater {
    return LayoutInflater.from(context)
  }

  @Provides
  fun provideResources(@ApplicationScope context: Context): Resources {
    return context.resources
  }

  @Provides
  fun provideTimberTree(): Timber.Tree {
    return Pine.Builder()
      .addPackageReplacePattern(app.packageName, "SCHB")
      .grow()
  }

  @Provides
  fun provideSharedPreferences(@ApplicationScope context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
  }

  @Provides
  fun provideRealmProvider(): RealmProvider {
    return object : RealmProvider {
      override fun getRealm() = Realm.getDefaultInstance()
    }
  }

  @Provides
  @Singleton
  fun providePicasso(@ApplicationScope context: Context) : Picasso {
    return Picasso.with(context)
  }
}