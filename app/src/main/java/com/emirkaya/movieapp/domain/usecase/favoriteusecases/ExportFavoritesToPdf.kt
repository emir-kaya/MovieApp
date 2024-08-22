package com.emirkaya.movieapp.domain.usecase.favoriteusecases

import android.content.Context
import android.os.Environment
import android.util.Log
import com.emirkaya.movieapp.domain.repository.FavoriteMovieRepository
import com.emirkaya.movieapp.util.ImageUtil
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.element.Image
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ExportFavoritesToPdf @Inject constructor(
    private val favoriteMovieRepository: FavoriteMovieRepository
) {
    suspend fun execute(context: Context): File = withContext(Dispatchers.IO) {
        val favoriteMovies = favoriteMovieRepository.getAllFavorites()

        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "FavoriteMovies_$timestamp.pdf"
        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(downloadsDir, fileName)

        val pdfWriter = PdfWriter(file)
        val pdfDocument = PdfDocument(pdfWriter)
        val document = Document(pdfDocument)

        favoriteMovies.forEach { movie ->
            val imageUrl = ImageUtil.buildImageUrl(movie.posterPath)
            try {
                val inputStream = URL(imageUrl).openStream()
                val tempFile = File.createTempFile("temp_image", ".jpg", context.cacheDir)
                tempFile.outputStream().use { inputStream.copyTo(it) }

                val image = Image(ImageDataFactory.create(tempFile.absolutePath))
                image.scaleToFit(100f, 150f)
                document.add(image)

                tempFile.delete()
            } catch (e: Exception) {
                document.add(Paragraph("Image not available"))
                e.printStackTrace()
            }

            document.add(Paragraph("Title: ${movie.title ?: "N/A"}"))
            document.add(Paragraph("Release Date: ${movie.releaseDate ?: "N/A"}"))
            document.add(Paragraph("Rating: ${movie.voteAverage ?: "N/A"}"))
            document.add(Paragraph("---------------------------"))
        }

        document.close()

        return@withContext file
    }
}