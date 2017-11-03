// import java.sql.ResultSet;
// import java.sql.SQLException;
//
// import org.aspectj.lang.annotation.After;
// import org.aspectj.lang.annotation.Before;
//
// import model.Computer;
//
// @RunWith(MockitoJUnitRunner.class)
/// ** Test class for ServiceComputer. */
// public final class ComputerDaoImplTest extends TestCase {
// private ResultSet resultSet;
//
// @Override
// @Before
// public void setUp() {
// resultSet = Mockito.mock(ResultSet.class);
// }
//
// @Override
// @After
// public void tearDown() {
//
// }
//
// /** Test the mapper function for computer.
// * @throws SQLException if the request return an exception */
// @Test
// public void testMapComputer() throws SQLException {
// // Mockito.when(resultSet.getRow()).thenReturn(1);
// //
// // Mockito.when(resultSet.getLong("id")).thenReturn(1L);
// // Mockito.when(resultSet.getString("name")).thenReturn("computer_test");
// //
// Mockito.when(resultSet.getTimestamp("introduced")).thenReturn(Timestamp.valueOf("1991-01-01
// // 00:00:00"));
// //
// Mockito.when(resultSet.getTimestamp("discontinued")).thenReturn(Timestamp.valueOf("1991-01-01
// // 00:00:00"));
// // Mockito.when(resultSet.getLong("company_id")).thenReturn(1L);
// //
// // Computer computer = ComputerDAOImpl.mapComputer(resultSet);
// // assertEquals(computer.getId(), 1L);
// // assertEquals(computer.getName(), "computer_test");
// // assertEquals(computer.getIntroduced(), Timestamp.valueOf("1991-01-01
// // 00:00:00"));
// // assertEquals(computer.getDiscontinued(), Timestamp.valueOf("1991-01-01
// // 00:00:00"));
// // assertEquals(computer.getCompanyId(), 1L);
// }
//
// /** Test the mapper function for computer.
// * @throws SQLException if the request return an exception */
// @Test
// public void testMapComputerAllNull() throws SQLException {
// Mockito.when(resultSet.getRow()).thenReturn(1);
//
// Mockito.when(resultSet.getLong("id")).thenReturn(1L);
// Mockito.when(resultSet.getString("name")).thenReturn(null);
// Mockito.when(resultSet.getTimestamp("introduced")).thenReturn(null);
// Mockito.when(resultSet.getTimestamp("discontinued")).thenReturn(null);
// Mockito.when(resultSet.getLong("company_id")).thenReturn(0L);
//
// Computer computer = ComputerDAOImpl.mapComputer(resultSet);
// assertEquals(computer.getId(), 1L);
// assertEquals(computer.getName(), null);
// assertEquals(computer.getIntroduced(), null);
// assertEquals(computer.getDiscontinued(), null);
// assertEquals(computer.getCompanyId(), 0L);
// }
//
// /** Test the mapper function for computer.
// * @throws SQLException if the request return an exception */
// @Test
// public void testMapComputerNameEmpty() throws SQLException {
// Mockito.when(resultSet.getRow()).thenReturn(1);
//
// Mockito.when(resultSet.getLong("id")).thenReturn(1L);
// Mockito.when(resultSet.getString("name")).thenReturn("");
// Mockito.when(resultSet.getTimestamp("introduced")).thenReturn(null);
// Mockito.when(resultSet.getTimestamp("discontinued")).thenReturn(null);
// Mockito.when(resultSet.getLong("company_id")).thenReturn(1L);
//
// Computer computer = ComputerDAOImpl.mapComputer(resultSet);
// assertEquals(computer.getId(), 1L);
// assertEquals(computer.getName(), "");
// assertEquals(computer.getIntroduced(), null);
// assertEquals(computer.getDiscontinued(), null);
// assertEquals(computer.getCompanyId(), 1L);
// }
// }