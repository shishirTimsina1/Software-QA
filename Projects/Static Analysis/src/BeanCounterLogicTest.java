import gov.nasa.jpf.vm.Verify;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Code by @author Wonsun Ahn
 * 
 * <p>Uses the Java Path Finder model checking tool to check BeanCounterLogic in
 * various modes of operation. It checks BeanCounterLogic in both "luck" and
 * "skill" modes for various numbers of slots and beans. It also goes down all
 * the possible random path taken by the beans during operation.
 */

public class BeanCounterLogicTest {
	private static BeanCounterLogic logic; // The core logic of the program
	private static Bean[] beans; // The beans in the machine
	private static String failString; // A descriptive fail string for assertions

	private static int slotCount; // The number of slots in the machine we want to test
	private static int beanCount; // The number of beans in the machine we want to test
	private static boolean isLuck; // Whether the machine we want to test is in "luck" or "skill" mode

	private int getInFlightBeanCount(BeanCounterLogic logic, int slotCount) {
		int inFlight = 0;
		for (int yPos = 0; yPos < slotCount; yPos++) {
			int xPos = logic.getInFlightBeanXPos(yPos);
			if (xPos != BeanCounterLogic.NO_BEAN_IN_YPOS) {
				inFlight++;
			}
		}
		return inFlight;
	}

	private int getInSlotsBeanCount(BeanCounterLogic logic, int slotCount) {
		int inSlots = 0;
		for (int i = 0; i < slotCount; i++) {
			inSlots += logic.getSlotBeanCount(i);
		}
		return inSlots;
	}

	/**
	 * Sets up the test fixture.
	 */
	@BeforeClass
	public static void setUp() {
		if (Config.getTestType() == TestType.JUNIT) {
			slotCount = 5;
			beanCount = 3;
			isLuck = true;
		} else if (Config.getTestType() == TestType.JPF_ON_JUNIT) {
			slotCount = Verify.getInt(1, 5);
			beanCount = Verify.getInt(1, 3);
			isLuck = Verify.getBoolean();

		} else {
			assert (false);
		}

		// Create the internal logic
		logic = BeanCounterLogic.createInstance(slotCount);
		// Create the beans
		beans = new Bean[beanCount];
		for (int i = 0; i < beanCount; i++) {
			beans[i] = Bean.createInstance(slotCount, isLuck, new Random(42));
		}

		// A failstring useful to pass to assertions to get a more descriptive error.
		failString = "Failure in (slotCount=" + slotCount
				+ ", beanCount=" + beanCount + ", isLucky=" + isLuck + "):";
	}

	@AfterClass
	public static void tearDown() {
	}

	/**
	 * Test case for void reset(Bean[] beans).
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 * Invariants: If beanCount is greater than 0,
	 *             remaining bean count is beanCount - 1
	 *             in-flight bean count is 1 (the bean initially at the top)
	 *             in-slot bean count is 0.
	 *             If beanCount is 0,
	 *             remaining bean count is 0
	 *             in-flight bean count is 0
	 *             in-slot bean count is 0.
	 */
	@Test
	public void testReset() {
		// TODO: Implement
		logic.reset(beans);
		if (beanCount > 0) {
			Assert.assertEquals(failString, beanCount - 1, logic.getRemainingBeanCount());
			Assert.assertEquals(failString, 1, this.getInFlightBeanCount(logic, slotCount));
			Assert.assertEquals(failString, 0, this.getInSlotsBeanCount(logic, slotCount));
		}
		if (beanCount == 0) {
			Assert.assertEquals(failString, 0, logic.getRemainingBeanCount());
			Assert.assertEquals(failString, 0, this.getInFlightBeanCount(logic, slotCount));
			Assert.assertEquals(failString, 0, this.getInSlotsBeanCount(logic, slotCount));
		}
		//System.out.println(failString);
	}

	/**
	 * Test case for boolean advanceStep().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: After each advanceStep(),
	 *             all positions of in-flight beans are legal positions in the logical coordinate system.
	 */
	@Test
	public void testAdvanceStepCoordinates() {
		// TODO: Implement
		logic.reset(beans);
		while (logic.advanceStep()) {
			for (int i = 0; i < slotCount; i++) {
				int xPos = logic.getInFlightBeanXPos(i);
				Assert.assertTrue(failString, xPos == -1 || (xPos >= 0 && xPos <= i));
			}
		}
	}

	/**
	 * Test case for boolean advanceStep().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: After each advanceStep(),
	 *             the sum of remaining, in-flight, and in-slot beans is equal to beanCount.
	 */
	@Test
	public void testAdvanceStepBeanCount() {
		logic.reset(beans);
		while (logic.advanceStep()) {
			int inFlight = this.getInFlightBeanCount(logic, slotCount);
			int remaining = logic.getRemainingBeanCount();
			int inSlot = this.getInSlotsBeanCount(logic, slotCount);
			int sum = inFlight + remaining + inSlot;
			Assert.assertEquals(failString, beanCount, sum);
		}
	}

	/**
	 * Test case for boolean advanceStep().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: After the machine terminates,
	 *             remaining bean count is 0
	 *             in-flight bean count is 0
	 *             in-slot bean count is beanCount.
	 */
	@Test
	public void testAdvanceStepPostCondition() {
		// TODO: Implement
		logic.reset(beans);
		while (logic.advanceStep()) {
		}
		int inFlight = this.getInFlightBeanCount(logic, slotCount);
		int remaining = logic.getRemainingBeanCount();
		int inSlot = this.getInSlotsBeanCount(logic, slotCount);

		Assert.assertEquals(failString, 0, inFlight);
		Assert.assertEquals(failString, beanCount, inSlot);
		Assert.assertEquals(failString, 0, remaining);
	}
	
	/**
	 * Test case for void lowerHalf()().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.lowerHalf().
	 * Invariants: After the machine terminates,
	 *             remaining bean count is 0
	 *             in-flight bean count is 0
	 *             in-slot bean count is beanCount.
	 *             After calling logic.lowerHalf(),
	 *             slots in the machine contain only the lower half of the original beans.
	 *             Remember, if there were an odd number of beans, (N+1)/2 beans should remain.
	 *             Check each slot for the expected number of beans after having called logic.lowerHalf().
	 */
	@Test
	public void testLowerHalf() {
		// TODO: Implement
		logic.reset(beans);
		while (logic.advanceStep()) {
		}
		int inFlight = this.getInFlightBeanCount(logic, slotCount);
		int remaining = logic.getRemainingBeanCount();
		int inSlot = this.getInSlotsBeanCount(logic, slotCount);
		Assert.assertEquals(failString, 0, inFlight);
		Assert.assertEquals(failString, beanCount, inSlot);
		Assert.assertEquals(failString, 0, remaining);
		int[] copyArr = new int[slotCount];
		int mid = Math.floorDiv(slotCount, 2);
		for (int i = 0; i < slotCount; i++) {
			copyArr[i] = BeanCounterLogicTest.logic.getSlotBeanCount(i);
		}

		logic.lowerHalf();
		for (int j = 0; j < mid - 1; j++) {
			Assert.assertEquals(failString, copyArr[j], BeanCounterLogicTest.logic.getSlotBeanCount(j));
		}
		


	}
	
	/**
	 * Test case for void upperHalf().
	 * Preconditions: None.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.upperHalf().
	 * Invariants: After the machine terminates,
	 *             remaining bean count is 0
	 *             in-flight bean count is 0
	 *             in-slot bean count is beanCount.
	 *             After calling logic.upperHalf(),
	 *             slots in the machine contain only the upper half of the original beans.
	 *             Remember, if there were an odd number of beans, (N+1)/2 beans should remain.
	 *             Check each slot for the expected number of beans after having called logic.upperHalf().
	 */
	@Test
	public void testUpperHalf() {
		// TODO: Implement
		logic.reset(beans);
		while (logic.advanceStep()) {
		}
		int inFlight = this.getInFlightBeanCount(logic, slotCount);
		int remaining = logic.getRemainingBeanCount();
		int inSlot = this.getInSlotsBeanCount(logic, slotCount);
		Assert.assertEquals(failString, 0, inFlight);
		Assert.assertEquals(failString, beanCount, inSlot);
		Assert.assertEquals(failString, 0, remaining);
		int[] copyArr = new int[slotCount];
		int mid = Math.floorDiv(slotCount, 2);
		for (int i = 0; i < slotCount; i++) {
			copyArr[i] = BeanCounterLogicTest.logic.getSlotBeanCount(i);
		}

		logic.upperHalf();
		for (int j = slotCount - 1; j > mid + 1; j--) {
			Assert.assertEquals(failString, copyArr[j], 
				  BeanCounterLogicTest.logic.getSlotBeanCount(j));
		}
	}
	
	/**
	 * Test case for void repeat().
	 * Preconditions: The machine is operating in skill mode.
	 * Execution steps: Call logic.reset(beans).
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 *                  Call logic.repeat();
	 *                  Call logic.advanceStep() in a loop until it returns false (the machine terminates).
	 * Invariants: Bean count in each slot is identical after the first run and second run of the machine. 
	 */
	@Test
	public void testRepeat() {
		if (!isLuck) {
			// TODO: Implement
			logic.reset(beans);
			while (logic.advanceStep()) {
			}
			int[] copyArr = new int[slotCount];
			for (int i = 0; i < slotCount; i++) {
				copyArr[i] = BeanCounterLogicTest.logic.getSlotBeanCount(i);
			}
			logic.repeat();
			while (logic.advanceStep()) {
			}
			for (int i = 0; i < slotCount; i++) {
				Assert.assertEquals(failString, copyArr[i],
					  BeanCounterLogicTest.logic.getSlotBeanCount(i));
			}
		}
	}
}
