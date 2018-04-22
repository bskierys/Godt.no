package pl.ipebk.schibsted.cache.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmIngredient : RealmObject() {

  @PrimaryKey
  open var id: Long? = null

  open var name: String? = null

  open var elements: RealmList<RealmIngredientElement> = RealmList()
}