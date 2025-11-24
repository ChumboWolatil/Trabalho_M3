# Calendario (Agenda) - Projeto Android (Kotlin + Compose)

Este projeto é um esqueleto pronto para importar no Android Studio e contém:
- Camadas: UI / Domain / Data
- ViewModel com UDF (StateFlow)
- Navigation Compose
- Integração com Room (SQLite) e esqueleto para Firebase (Firestore + Auth)



## Como usar
1. Abra o Android Studio > Open > selecione a pasta `calendario_project` (ou o ZIP).
2. Se você for usar Firebase:
   - Coloque o `google-services.json` em `app/`.
   - Ative o plugin `com.google.gms.google-services` (já presente) e ajuste a versão do BOM se desejar.
3. Sincronize (Gradle) e faça Run.

## Observações
- Este é um esqueleto: para rodar completamente, ajuste dependências e versões conforme seu ambiente.
- Não inclui arquivos binários pesados (keystore, google-service.json real). Há um placeholder em `app/google-services.json.example`.
- O código contém placeholders mínimos para telas — substitua pela UI desejada.

