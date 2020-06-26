# Relazione finale
## Introduzione
La seguente relazione mira a mettere in evidenza **1. Scelte progettuali** e **2. Dettagli implementativi** del progetto per Metodi Avanzati di Programmazione sviluppato da Luigi Porcelli nell'A.A. 2019/2020.
Il progetto in esame è un'[avventura testuale](https://it.wikipedia.org/wiki/Avventura_testuale), la cui struttura è ispirata da prodotti storici dello stesso genere, tra cui le [avventure testuali di Scott Adams](http://www.msadams.com/).  

## Scelte progettuali
- Le classi sono strutturate in modo da avere responsabilità precise, nel rispetto della tassonomia [ECB](https://en.wikipedia.org/wiki/Entity-control-boundary).
- L'avventura testuale sviluppata fa uso del framework `Swing` per l'interfaccia grafica, nella quale l'utente può leggere il testo di output ed inserire comandi in modo semplice e lineare. L'interfaccia grafica ci permette di eseguire azioni non inerenti alla trama del gioco (cambio della lingua, salvataggio e caricamento dei progressi, ecc.) senza doverle riconoscere come comandi di gioco (necessario in un applicativo CLI).

-  Le stringhe di output vengono salvate in molteplici file: grazie all'utilizzo della classe `ResourceBundle` riusciamo ad ottenere una localizzazione completa dell'intero software grazie all'individuazione a Runtime della lingua della JVM sulla quale il software viene eseguito. Il programma è localizzato in due lingue: *italiano* e *inglese*.

- La logica dell'ambiente circostante è contenuta nella classe `Room`. 
  - Gli oggetti della suddetta classe sono connessi tra di loro attraverso un oggetto `AdjacentRoom`, il quale si occupa di valutare la presenza di ostacoli sul cammino verso una determinata direzione attraverso un riferimento all'ID dell'oggetto `Room` che vogliamo ottenere ed un eventuale oggetto `Obstacle`.

- Lo stato corrente di gioco è mantenuto nella classe statica `GameProgress`, il quale mantiene un riferimento all'oggetto della classe `Room` nella quale si trova il giocatore. 

- Per il riconoscimento dei comandi, ci serviamo della classe `Parser`, la quale interpreta la stringa in ingresso utilizzando un'espressione regolare. In base all'input, riconosce il tipo di azione che si vuole svolgere e il soggetto, andando a chiamare i metodi coerenti con l'espressione.
- È possibile conservare in un file i progressi di gioco (come contenuto dell'inventario, posizione corrente, progressi nella mappa) attraverso il `MenuItem` con etichetta *salva*, per poter riprendere il gioco in un secondo momento, aprendo lo stesso file attraverso il `MenuItem` con etichetta *Carica*.

- La mappa di gioco (ovvero l'insieme degli oggetti `Room`) viene letta da file, rendendo ulteriormente indipendente il software dalla storia sviluppata.

- Le classi `Inventory` e `UIFrame` sono state scelte ed implementate come classi Singleton, proprio perché, ad ogni esecuzione, avremo un solo frame principale ed un solo inventario. 
## Alcuni dettagli implementativi

- Il testo viene inserito all'interno di un `JEditorPane` simulando il "Typewriter Effect", ovvero la stampa di caratteri in modo animato: ciò è possibile creando un nuovo `Thread` per ogni stringa che vogliamo stampare, inserendo nel `JEditorPane` un carattere per volta, e chiamando il metodo `sleep()` su questo Thread dopo la stampa di ogni carattere.
	- Per evitare la scrittura simultanea di più stringhe contemporaneamente, ognuno di questi Thread di stampa delle stringhe viene inserito in una [`LinkedBlockingQueue`](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/LinkedBlockingQueue.html), dal quale possiamo ottenere **atomicamente** l'elemento in testa. Tali elementi vengono poi eseguiti da un'ulteriore Thread, il quale verifica che: 1)Nella `LinkedBlockingQueue`ci sia un elemento in testa, e 2) l'elemento in testa (un thread) non sia in esecuzione. Con la *2* ci assicuriamo che venga stampata una stringa per volta. La *1* è necessaria poiché potremmo voler ripulire il JEditorPane nel quale andiamo ad inserire i caratteri della stringa, ergo anche le stringhe in attesa di stampa fino a quel momento devono essere ripulite.  
La scelta di voler inserire un'istanza di thread nella coda anziché la stringa che vogliamo stampare è dovuta al fatto che vogliamo verificare che vi sia un Thread in esecuzione che si occupa di stampare quella stessa stringa.  
In un dato momento, con n stringhe in attesa di stampa, avremo n+1 processi in esecuzione (n thread handler ed 1 di effettiva stampa).

- Per permettere l'utilizzo di sinonimi (*destra* al posto di *est*, *prendi* al posto di *raccogli* ecc.), gli enumerativi `Direction`e `Command` sono forniti di una `Map`, la quale ha come chiave un array di stringhe (i sinonimi) e come valore l'enum corrispondente, in modo da poter ottenere il valore dell'enumeratore sul quale far lavorare la classe `Parser` nel riconoscimento del comando. 
  - Proprio attraverso questo processo di riconoscimento di comandi e direzioni possiamo accettare stringhe solo nella lingua corrente del gioco, poiché la `Map` dei sinonimi vieni aggiornata ogni volta che vi è un cambio di lingua.
- I nomi degli strumenti vengono recuperati a Runtime da un file `.properties`, utilizzando come chiave il nome della classe. 
   - Analogamente ai comandi/direzioni, in questo modo possiamo accettare nomi di strumenti solo nella lingua corrente, andando a prelevare i nomi da file adeguati alla lingua eseguita.