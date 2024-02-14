# COMP242-AVL-Tree

## Project Overview

This application is designed to analyze electricity supply data for the Gaza Strip, addressing the chronic electricity deficit and its impacts on essential services and the economy. It utilizes data provided by the Gaza Electricity Distribution Company (GEDCO) and implements a data structure using combined AVL trees to efficiently manage and analyze the electricity data.

## Features

- **Data Structure**: Implements combined AVL trees to manage daily electricity records efficiently.
- **Graphical User Interface**: Developed with JavaFX (without using Scene Builder) to provide an interactive user experience.
- **Electricity Record Management**: Allows users to insert, update, delete, and search for electricity records by date.
- **Traversal and Display**: Offers options to traverse and print the Years, Months, and Days Trees level by level, and display their heights.
- **Statistical Analysis**: Enables analysis of specific and total statistics for given days, months, and years, including total, average, maximum, and minimum values.
- **Data Persistence**: Includes a feature to save updated trees back to a new file, maintaining the original format.

## Installation

Ensure you have Java and JavaFX set up on your system. Clone the repository to your local machine:
`git clone https://github.com/MoShabaneh/COMP242-AVL-Tree.git`

## Usage

1. Run the application through your Java IDE or from the command line.
2. Use the file chooser to load the `Electricity.csv` file.
3. Navigate between the Management Screen and Statistics Screen to manage records and view statistics.
4. In the Save Screen, save the updated trees to a new file via a file chooser.

### Input File Format

The `Electricity.csv` file contains daily electricity records in the following format:
`Date, Israeli_Lines_MWs, Gaza_Power_Plant_MWs, Egyptian_Lines_MWs, Total_daily_Supply_available_in_MWs, Overall_demand_in_MWs, Power_Cuts_hours_day_400mg, Temp`

Each record is separated by commas.
