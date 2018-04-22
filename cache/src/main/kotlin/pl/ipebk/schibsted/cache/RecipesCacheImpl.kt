package pl.ipebk.schibsted.cache

import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.realm.Case
import io.realm.Realm
import pl.ipebk.schibsted.cache.mapper.RecipeEntityMapper
import pl.ipebk.schibsted.cache.model.RealmRecipe
import pl.ipebk.schibsted.data.model.RecipeEntity
import pl.ipebk.schibsted.data.repository.RecipesCache
import javax.inject.Inject

/**
 * Cached implementation for saving Recipes instances. This class implements the
 * [RecipesCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class RecipesCacheImpl @Inject constructor(
  private val isCachedRepository: IsCachedRepository,
  private val realmProvider: RealmProvider,
  private val mapper: RecipeEntityMapper) : RecipesCache {

  override fun isCached(): Boolean {
    return isCachedRepository.isCached()
  }

  override fun getAllRecipes(): Single<List<RecipeEntity>> {
    return Single.create { emitter: SingleEmitter<List<RealmRecipe>> ->
      var realm: Realm? = null
      try {
        realm = realmProvider.getRealm()
        val results = realm.where(RealmRecipe::class.java).findAll()
        if (!emitter.isDisposed) {
          emitter.onSuccess(results)
        }

      } catch (ex: Exception) {
        if (!emitter.isDisposed) {
          emitter.onError(ex)
        }
      } finally {
        realm?.close()
      }
    }.map { list ->
        list.map {
          val entity = mapper.mapFromRealm(it)
          entity
        }
      }
  }

  override fun searchForRecipe(phrase: String): Single<List<RecipeEntity>> {
    return Single.create { emitter: SingleEmitter<List<RealmRecipe>> ->
      var realm: Realm? = null
      try {
        realm = realmProvider.getRealm()

        var query = realm.where(RealmRecipe::class.java)

        query = query.contains("ingredients.elements.name", phrase, Case.INSENSITIVE)
        query = query.or().contains("title", phrase, Case.INSENSITIVE)

        val results = query.findAll()

        if (!emitter.isDisposed) {
          emitter.onSuccess(results)
        }

      } catch (ex: Exception) {
        if (!emitter.isDisposed) {
          emitter.onError(ex)
        }
      } finally {
        realm?.close()
      }
    }.map { list ->
        list.map { mapper.mapFromRealm(it) }
      }
  }

  override fun getRecipe(id: Long): Single<RecipeEntity> {
    return Single.create { emitter: SingleEmitter<RealmRecipe> ->
      var realm: Realm? = null
      try {
        realm = realmProvider.getRealm()
        val results = realm.where(RealmRecipe::class.java)
          .equalTo("id", id).findFirst()
        if (!emitter.isDisposed) {
          emitter.onSuccess(results!!)
        }

      } catch (ex: Exception) {
        if (!emitter.isDisposed) {
          emitter.onError(ex)
        }
      } finally {
        realm?.close()
      }
    }.map { mapper.mapFromRealm(it) }
  }

  override fun saveRecipes(recipes: List<RecipeEntity>): Completable {
    // TODO: for simplicity I am deleting all and adding them again. This should not be done in
    // TODO: real-life app

    return Completable.create { emitter: CompletableEmitter ->
      var realm: Realm? = null
      try {
        realm = realmProvider.getRealm()
        realm.executeTransaction {
          realm.deleteAll()
          recipes.forEach { mapper.mapToRealm(it, realm) }
        }
        isCachedRepository.setWasCached()
        if (!emitter.isDisposed) {
          emitter.onComplete()
        }

      } catch (ex: Exception) {
        if (!emitter.isDisposed) {
          emitter.onError(ex)
        }
      } finally {
        realm?.close()
      }
    }
  }
}