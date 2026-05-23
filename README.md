# Shifty - Android Shift Manager

A comprehensive Android application built in Java to view and manage work shifts.

## Features

- **View Shifts**: Display all scheduled work shifts in a calendar or list view
- **Create Shifts**: Add new work shifts with date, time, and details
- **Edit Shifts**: Modify existing shift information
- **Delete Shifts**: Remove shifts from your schedule
- **Shift Details**: Store shift information including:
  - Date and time
  - Duration
  - Location
  - Notes
  - Shift type (Regular, Overtime, etc.)
- **Notifications**: Get reminders for upcoming shifts
- **Offline Support**: Works without internet connection

## Project Structure

```
Shifty/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/shifty/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── activity/
│   │   │   │   ├── fragment/
│   │   │   │   ├── adapter/
│   │   │   │   ├── database/
│   │   │   │   ├── model/
│   │   │   │   └── util/
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── values/
│   │   │   │   ├── drawable/
│   │   │   │   └── menu/
│   │   │   └── AndroidManifest.xml
│   │   ├── test/
│   │   └── androidTest/
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

## Technologies

- **Language**: Java
- **Android API**: API 21 (Android 5.0) and above
- **Database**: SQLite with Room Persistence Library
- **UI**: Android Material Design
- **Architecture**: MVVM with LiveData

## Getting Started

### Prerequisites

- Android Studio 4.0 or higher
- JDK 8 or higher
- Android SDK 21 and above

### Installation

1. Clone the repository
```bash
git clone https://github.com/lucipurrrr/Shifty.git
cd Shifty
```

2. Open in Android Studio

3. Sync Gradle files

4. Run the app on an emulator or physical device

## Usage

### Creating a Shift
1. Tap the "Add Shift" button
2. Fill in the shift details
3. Tap "Save"

### Viewing Shifts
1. View shifts in the calendar or list view
2. Tap a shift to see detailed information

### Editing a Shift
1. Tap a shift to view details
2. Tap "Edit"
3. Modify the information
4. Tap "Save"

### Deleting a Shift
1. Long press a shift
2. Tap "Delete"
3. Confirm deletion

## Contributing

Contributions are welcome! Please feel free to submit pull requests.

## License

MIT License - see LICENSE file for details
