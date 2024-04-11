# Progetto per reti di calcolatori
---

### Creato da [me](https://github.com/da4do0)

# Introduzione
---
Il progetto consiste solo in un server capace di gestire le richieste da parte dei client (terminali - cmd/powershell/ubuntu) che eseguono richieste di: nuovo parcheggio/trova la mia macchina(ti indica il posto del tuo parcheggio)/paga parcheggio(2$/h).

# Installazione e utilizzo
---

### Server
Scaricare Il file jar da questa pagina [release](https://github.com/da4do0/Server_parking/releases/tag/1.0).

Una volta scaricato (assicurarsi di avere installata la macchina virtuale di java), create una cartella per poter metterci all'interno il file jar, infine avviarlo tramite il comando java (esempio qui sotto).
> [!TIP]
> Il server si mettera' in ascolto sulla porta 5001, in caso la porta sia occupata provera' con quella dopo finche' non riuscira' a connettersi.

```console
  java -jar Server_parking.java
```
---
# Caratteristiche del server
Il server e' un applicazione da linea di comando scritta solamente in java. Il server per poter gestire tutti i client che si vogliono connettere utilizza i thread, e per salvare tutti i dati ogni volta che qualuno effettua il checkin o il checkout viene utilizzato il formato json gestito dalla classe Gson. Il server appena riceve un client sulla "porta di benvenuto" crea un thread creando un oggetto ClientHandler. Tramite la classe ClientHandler il client e' libero di comunicare con il server (ClientHandler) tramite un menu di selezione; in questo menu il client ha la possibilita' di chiedere un parcheggio se disponibile, chiedere in quale posto ha parcheggiato la macchina ed infine di pagare ua volta aver finito di usufruire del servizio. Ogni volta che un client ricevera' un nuovo parcheggio o che paghera' per il servizio, il file json verra' aggiornato.

Qui sotto un esempio del file json:

```console
  [
   {
      "namePlace":"A1",
      "plate":"it abc 123",
      "enter":1712792936207
   },
   {
      "namePlace":"A2",
      "plate":"",
      "enter":0
   },
   {
      "namePlace":"A3",
      "plate":"",
      "enter":0
   }
]
```

# Funzioni Principali:
---
## Prenotazione del Parcheggio
L'utente sara' in grado di chiede al server un parcheggio, il server dopo essersi aggiornato tramite il file json effettua una ricerca cosi da verificare se esiste un parcheggio libero; se si lo comunica al client e lo registra sul file json cosi da aggiornare anche gli altri client in futuro, se no il il server comunichera' al client che il parcheggio e' pieno.

## Trova la mia Macchina
L'utente dopo aver inserito la propira targa, se questa sara' gia' presente, potra' chiedere al server indicazioni riguardo il posteggio della propria macchina.

## Pagamento Servizio
Una volta usufruito del servizio, il client potra' comunicare al server che intende pagare per il servizio, il client quindi andra' a recuperare la data di entrata e chiedere al sistema al data attuale cosi' da poter fare la differenza e calcolare la tariffa totale.

---
> [!IMPORTANT]
> Release future: Client grafico, Server grafico.
