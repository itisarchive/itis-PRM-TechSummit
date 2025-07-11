package pl.edu.pja.kdudek.techsummit

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import pl.edu.pja.kdudek.techsummit.ui.theme.TechSummitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TechSummitTheme {
                Screen()
            }
        }
    }
}

@Composable
fun Screen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header()
            Spacer(modifier = Modifier.height(8.dp))
            Info()
            Agenda()
        }
    }
}

@Composable
fun ColumnScope.Agenda() {
    Text(
        modifier = Modifier.align(Alignment.Start),
        text = stringResource(R.string.home_agenda),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
    val agenda = stringArrayResource(R.array.agenda)
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        agenda.forEach {
            val parts = it.split("|")
            AgendaItem(parts[0], parts[1], parts.getOrNull(2))
        }
    }
}

@Composable
fun Header() {
    Image(
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape),
        painter = painterResource(R.drawable.logo),
        contentDescription = "Tech Summit Logo"
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = stringResource(R.string.app_name),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
    Text(
        text = stringResource(R.string.home_title),
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.secondary
    )
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, "https://www.techsummit.com".toUri())
            context.startActivity(intent)
        }
    ) {
        Text("WWW")
    }
}

@Composable
fun Info() {
    Text(
        text = stringResource(R.string.home_date),
        fontWeight = FontWeight.Bold
    )
    Text(
        text = stringResource(R.string.home_place),
        fontSize = 14.sp
    )
    Text(
        text = stringResource(R.string.home_desc),
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun AgendaItem(
    time: String,
    title: String,
    speaker: String? = null,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = time,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )
            speaker?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.home_speaker, it),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    TechSummitTheme {
        Screen()
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    TechSummitTheme {
        AgendaItem(
            time = "10:00 - 11:00",
            title = "Keynote: Welcome to Tech Summit",
            speaker = "John Doe"
        )
    }
}
