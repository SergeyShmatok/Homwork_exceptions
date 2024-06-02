package ru.netology


abstract class Attachment {

    abstract val type: String
}


// ______Классы - вложения______


data class PhotoAttachment(val photo: Photo) : Attachment() {
    override val type: String = "photo"
}

data class VideoAttachment(val video: Video) : Attachment() {
    override val type: String = "video"
}


data class AudioAttachment(val video: Audio) : Attachment() {
    override val type: String = "audio"
}

data class FileAttachment(val video: File) : Attachment() {
    override val type: String = "file"
}

data class LinkAttachment(val video: Link) : Attachment() {
    override val type: String = "link"
}


// ________Контент________


data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String = "Описание фотографии"
)

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String = "Название видеозаписи",
    val description: String = "Текст описания видеозаписи",
    val duration: Int,
)

data class Audio(
    val id: Int,
    val ownerId: Int,
    val title: String = "Название композиции",
    val artist: String = "Исполнитель",
    val duration: Int,
)


data class File(
    val id: Int,
    val ownerId: Int,
    val title: String = "Название файла",
    val size: Int,
    val ext: String = "Расширение файла",
)

data class Link(

    val url: String = "URL ссылки",
    val title: String = "Заголовок ссылки",
    val caption: String = "Подпись ссылки",
    val description: String = "Описание ссылки",
    val previewUrl: String = "URL страницы с контентом для предпросмотра",
)

