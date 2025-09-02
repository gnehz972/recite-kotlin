package com.bocc.recite.kotlin.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bocc.recite.R
import com.bocc.recite.kotlin.main.viewmodel.SentenceViewMode
import com.bocc.recite.kotlin.repository.data.DailySentence
import com.bocc.recite.kotlin.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: SentenceViewMode = hiltViewModel()
) {
    val sentenceState by viewModel.sentence.collectAsStateWithLifecycle()
    var currentIndex by remember { mutableIntStateOf(0) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        when (val result = sentenceState) {
            null -> {
                // Loading state
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            else -> {
                when {
                    result.isSuccess -> {
                        val sentences: List<DailySentence> = result.getOrNull() ?: emptyList()
                        when {
                            sentences.isEmpty() -> {
                                EmptyState()
                            }
                            sentences.size == 1 -> {
                                DailySentenceCard(
                                    sentence = sentences[0],
                                    onNext = { /* No next available */ }
                                )
                            }
                            else -> {
                                DailySentenceCard(
                                    sentence = sentences[currentIndex],
                                    onNext = {
                                        currentIndex = (currentIndex + 1) % sentences.size
                                    }
                                )
                            }
                        }
                    }
                    result.isFailure -> {
                        ErrorState(error = result.exceptionOrNull()?.message ?: "Unknown error")
                    }
                }
            }
        }
    }
}

@Composable
private fun DailySentenceCard(
    sentence: DailySentence,
    onNext: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = BackgroundCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Image
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(sentence.picture)
                    .crossfade(true)
                    .build(),
                contentDescription = "Daily sentence image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Title
            Text(
                text = "${stringResource(R.string.ciba_daily)} ${sentence.dateline}",
                style = MaterialTheme.typography.titleMedium,
                color = TextSecondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // Content
            Text(
                text = sentence.content,
                style = MaterialTheme.typography.bodyLarge,
                color = Yellow,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // Translation
            Text(
                text = sentence.note,
                style = MaterialTheme.typography.bodyMedium,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // Note
            Text(
                text = sentence.translation,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Next button
            Button(
                onClick = onNext,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
            ) {
                Text(
                    text = "Next Sentence",
                    color = White
                )
            }
        }
    }
}

@Composable
private fun EmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "No sentences available",
                style = MaterialTheme.typography.titleLarge,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ErrorState(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Error loading sentences",
                style = MaterialTheme.typography.titleLarge,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}
