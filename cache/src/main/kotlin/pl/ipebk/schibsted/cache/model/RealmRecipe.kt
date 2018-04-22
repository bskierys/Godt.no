package pl.ipebk.schibsted.cache.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class RealmRecipe : RealmObject() {

  @PrimaryKey
  open var id: Long? = null

  open var title: String = ""

  open var description: String = ""

  open var basicPortionNumber: Int = 0

  open var preparationTime: Int = 0

  open var publishedAt: Date? = null

  open var updatedAt: Date? = null

  open var ingredients: RealmList<RealmIngredient> = RealmList()

  open var steps: RealmList<RealmStep> = RealmList()

  open var images: RealmList<RealmImage> = RealmList()
}