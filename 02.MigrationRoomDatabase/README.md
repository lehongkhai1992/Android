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



### Jira

### Scrum agile

# nguyên tắc của agile
AGILE PRINCIPLES
▪ Iterative development
khuyến khích việc phát triển sản phẩm thông qua các chu kỳ phát triển ngắn, 
gọi là "iterations" hoặc "sprints". Mỗi iteration tập trung vào việc tạo ra 
một phiên bản hoạt động của sản phẩm có thể triển khai và đánh giá.


▪ Adaptive to changing requirements

"Adaptive to changing requirements" (Thích ứng với yêu cầu thay đổi) trong Agile 
thể hiện tư duy linh hoạt và khả năng thích ứng của quá trình phát triển


▪ Frequent delivery

▪ Close collaboration between cross-functional teams
nhấn mạnh tầm quan trọng của việc các teams (cross-functional teams) 
làm việc cùng nhau và hợp tác chặt chẽ để đạt được mục tiêu của dự án.

"The most efficient and effective method of conveying information to and 
within a development team is face-to-face conversation."

Nguyên tắc này đặt sự tập trung vào việc tạo ra môi trường làm việc nơi 
mà sự giao tiếp trực tiếp, tương tác trực tiếp giữa các thành viên của đội là ưu tiên hàng đầu.

- Phản hồi nhanh chóng
- Hiệu suất tối đa


▪ SCRUM methodologies
SCRUM là một framework quản lý dự án phát triển dựa trên nguyên tắc Agile
- Phân chia dự án thành các chu kỳ ngắn (Sprints): SCRUM chia dự án thành các đợt ngắn gọi là sprints, 
thường kéo dài từ 1 đến 4 tuần.
- Quản lý bằng Product Backlog và Sprint Backlog: Các yêu cầu được tổ chức trong Product Backlog, 
và mỗi Sprint được chọn một phần của Product Backlog để thực hiện trong Sprint Backlog.
- Các vai trò quan trọng: SCRUM có các vai trò như Product Owner, Scrum Master, và Development Team, 
mỗi vai trò có trách nhiệm riêng biệt trong quá trình phát triển.
- Sự linh hoạt và thích ứng: SCRUM tập trung vào việc thích ứng với sự thay đổi, đánh giá sản phẩm 
ở cuối mỗi Sprint để có thể thay đổi hướng phát triển.


▪ Kanban methodologies
Kanban là một phương pháp quản lý dự án và công việc dựa trên nguyên tắc về làm thế nào công việc 
được thực hiện và theo dõi thông lượng làm việc.
- Luồng công việc (Work Flow): Kanban tập trung vào việc quản lý và tối ưu hóa luồng công việc, 
từ khi công việc được thêm vào hệ thống đến khi nó được hoàn thành.
- Hạn chế công việc đồng thời (Work In Progress - WIP): Kanban áp dụng nguyên tắc hạn chế 
công việc đồng thời để giữ cho công việc luôn được ưu tiên và không bị quá tải.
- Tối ưu hóa qua thời gian thực: Kanban không có khái niệm về sprints, 
và công việc có thể được thực hiện và triển khai bất cứ khi nào nó sẵn sàng.
- Hệ thống bảng Kanban: Sử dụng bảng Kanban với các cột thể hiện các 
trạng thái của công việc để theo dõi quá trình làm việc.

Sự Tương Đồng và Khác Biệt:
Sự Tương Đồng: Cả SCRUM và Kanban đều tập trung vào việc cung cấp giá trị liên tục và linh hoạt đối với sự thay đổi.
Khác Biệt: SCRUM chia dự án thành các chu kỳ đặc trưng (sprints) và có cấu trúc nhất định với các 
sự kiện và vai trò cụ thể. Trong khi đó, Kanban tập trung vào tối ưu hóa quy trình công việc và không có khái niệm về các chu kỳ cố định.

# Jira

backlog
kanban
search
filter: access by ...
create issue, log a bugs
dashboard
link issues



Create dashboard and kanban board in Jira

Tip and trick for work



chat private or group chat

received tasks


# search jira
https://support.atlassian.com/jira-software-cloud/docs/what-is-advanced-search-in-jira-cloud/

