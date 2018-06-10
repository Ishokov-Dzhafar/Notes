package kot.notes.notes.data.entity

/**
 * Created by dzhafar on 10.06.18.
 */
open class SortNoteByDate: Comparator<NoteEntity> {
    override fun compare(o1: NoteEntity?, o2: NoteEntity?): Int {
        if (o2 != null && o1 != null) {
            return (o2.date!! - o1.date!!).toInt()
        } else return 0
    }

}