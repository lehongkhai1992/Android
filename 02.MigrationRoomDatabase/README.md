### Annotation process arguments

        https://kotlinlang.org/docs/kapt.html#annotation-processor-arguments
        
        kapt {
            arguments {
                arg("key", "value")
            }
        }

        kapt{
            arguments{
                arg("room.schemaLocation","$projectDir/schemas")
            }
        }

### Add new column

    @ColumnInfo(name = "student_email", defaultValue = "no email")
    var email : String

need to have defaultValue, it will fill into rows were created before

# You can add nullable column and default value

    @ColumnInfo(name = "course_name")
    var course : String? = null,

### Increment version Database and add autoMigrations

    @Database(
        entities = [Student::class],
        version = 2,
        exportSchema = true,
        autoMigrations = [AutoMigration(from = 1, to = 2)]
    )

### Change the name of column

change columns name is not simple change in dataclass
it need to some steps
# step 1: change up version and AutoMigration
# step 2: add Migration class
# step 3: add spec attribute in to @Database()
# step 4: change name in data class and add the fields fromColumnName = "student_email", toColumnName = "email_name"

    @Database(
        entities = [Student::class],
        version = 4,
        autoMigrations = [
            AutoMigration(from = 1, to = 2),
            AutoMigration(from = 2, to = 3),
            AutoMigration(from = 3, to = 4, spec = StudentDatabase.Migration3to4::class),
        ]
    )

    @RenameColumn(tableName = "student_info", fromColumnName = "student_email", toColumnName = "email_name")
    class Migration3to4: AutoMigrationSpec
### if you need to change the name of 2 columns, you need AutoMigration 2 times
# first time
    @Database(
        entities = [Student::class],
        version = 2,
        autoMigrations = [
            AutoMigration(from = 1, to = 2, spec = StudentDatabase.Migration1to2::class),
        ]
    )

    @RenameColumn(tableName = "student_info", fromColumnName = "student_email", toColumnName = "email_name")
    class Migration1to2: AutoMigrationSpec
# twice
    @Database(
        entities = [Student::class],
        version = 3,
        autoMigrations = [
            AutoMigration(from = 1, to = 2, spec = StudentDatabase.Migration1to2::class),
            AutoMigration(from = 2, to = 3, spec = StudentDatabase.Migration2to3::class),
        ]
    )

    @RenameColumn(tableName = "student_info", fromColumnName = "student_email", toColumnName = "email_name")
    class Migration1to2: AutoMigrationSpec

    @RenameColumn(tableName = "student_info", fromColumnName = "course_name", toColumnName = "subject_name")
    class Migration2to3: AutoMigrationSpec
### if you change one time -> it will be error


### same with delete 

        @Database(
        entities = [Student::class],
        version = 4,
        autoMigrations = [
            AutoMigration(from = 1, to = 2),
            AutoMigration(from = 2, to = 3),
            AutoMigration(from = 3, to = 4, spec = StudentDatabase.Migration3to4::class),
            AutoMigration(from = 4, to = 5, spec = StudentDatabase.Migration4to5::class),
        ]
    )

    @DeleteColumn(tableName = "student_info", ColumnName = "student_email")
    class Migration4to5: AutoMigrationSpec

# delete the field in data class
