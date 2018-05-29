package kot.notes.notes.data.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Uid
import java.io.Serializable
import javax.inject.Inject

/**
 * Created by dzhafar on 22.05.18.
 */

@Entity
@Uid(6857707533387470368L)
data class NoteEntity  (
        @Id var Id: Long = 0,
        var date:Long? = null,
        var text:String? = null
) : Serializable {

    @Inject
    constructor() : this(0, null, null) {}
}