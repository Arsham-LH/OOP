//salam. lazem dunestam begam ke barname tori neveshte shode ke belafasele baad az sabte sefaesh pul ro az
// hesabe karbar kam mikone (magar inke karbar sefaresh ro cancel kone). be khatere hamin hata agar admin gheymate
//kharid ya forushe kala ro taghiir bede, vaseye residegi be sefareshe moshtari hamun gheymate ghabli lahaz mishe ta hamun puli
//ke moshtari pardakht mikone ro admin daryaft kone va hesab ketab eshtebah nashe.
//dar zemn dokmeye residegi be sefareshat to tamrin zekr nashode bud ke ba dokmeye 'checkout orders' tu barname ovorde shode.




public class MainAdmin {
    public static void main(String[] args) {
        AdminManager manager = new AdminManager();
        AdminInputProcessor inputProcessor = new AdminInputProcessor(manager);
        inputProcessor.run();
    }
}
