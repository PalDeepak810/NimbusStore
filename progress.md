# NimbusStore Progress

## Milestone 1 - Project Initialization

- Created project structure
- Created backend and frontend directories
- Initialized Gateway Service
- Initialized Metadata Service
- Initialized Coordinator Service
- Initialized Storage Node Service
- Configured Git repository

Status: ✅ Completed

---

## Milestone 2 - Gateway Upload

- Configured multipart file upload
- Implemented UploadController
- Implemented UploadService
- Successfully received files through Gateway
- Tested using Postman

Status: ✅ Completed

---

## Milestone 3 - Metadata Service

- Configured PostgreSQL
- Created ObjectMetadata entity
- Created ObjectMetadataRepository
- Implemented ObjectService
- Implemented ObjectController
- UUID generation for every uploaded object
- Metadata persistence
- Tested using Postman

Status: ✅ Completed

---

## Milestone 4 - Gateway ↔ Metadata Integration

- Configured RestClient
- Created MetadataClient
- Gateway calls Metadata Service
- Metadata Service stores object information
- Gateway receives generated Object ID
- End-to-end metadata creation flow completed

Status: ✅ Completed

---

## Milestone 5 - Storage Node

- Created local storage directory
- Implemented StorageController
- Implemented StorageService
- File persistence on local disk
- Files stored using Object ID while preserving extension
- Tested directly using Postman

Status: ✅ Completed

---

## Milestone 6 - Gateway ↔ Storage Integration

- Configured Storage RestClient
- Created StorageClient
- Gateway uploads file to Storage Node
- Fixed multiple RestClient bean configuration
- End-to-end upload pipeline completed

Flow:

Client
→ Gateway
→ Metadata Service
→ Storage Node

Status: ✅ Completed

---

## Milestone 7 - Storage Node Download

- Implemented file retrieval from local storage
- Added download endpoint
- Implemented object lookup by Object ID
- File download tested successfully using Postman

Status: ✅ Completed

---

## Milestone 8 - Gateway Download

- Added download support to StorageClient
- Implemented DownloadService
- Implemented DownloadController
- Gateway now proxies download requests to Storage Node
- End-to-end download flow completed

Flow:

Client
→ Gateway
→ Storage Node
→ Gateway
→ Client

Status: ✅ Completed

---

# Current System

```
                    Client
                       │
                       ▼
                Gateway Service
                (Single Entry Point)
                 │              │
                 ▼              ▼
        Metadata Service    Storage Node
             │                  │
        PostgreSQL         Local File System
```

---

# Features Implemented

- ✅ Upload Object
- ✅ Download Object
- ✅ Metadata Persistence
- ✅ Local File Storage
- ✅ UUID-based Object Identification
- ✅ Service-to-Service Communication
- ✅ Independent Microservices
- ✅ End-to-End Upload Pipeline
- ✅ End-to-End Download Pipeline

---

# Next Milestone

## Chunking

Instead of storing an entire file:

```
movie.mp4
```

NimbusStore will split it into chunks.

```
movie.mp4
      │
      ▼
chunk-1
chunk-2
chunk-3
chunk-4
```

The Storage Node will store chunks instead of complete files.

Status: ⏳ Planned