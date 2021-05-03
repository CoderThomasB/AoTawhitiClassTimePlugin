package CoderThomasB.AoTawhitiClassTimePlugin;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TimeBlockTest {
    @Test
    public void TestGetBlockAtTime() {
        Assert.assertEquals(TimeBlock.NoSchool, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 2,
                        15, 31
                )));
        Assert.assertEquals(TimeBlock.EndOfSchool, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 3,
                        15, 31
                )));
        Assert.assertEquals(TimeBlock.BeforeSchool, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 3,
                        8, 41
                )));
        Assert.assertEquals(TimeBlock.MorningBreak, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 3,
                        11, 15
                )));
        Assert.assertEquals(TimeBlock.Lunch, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 3,
                        12, 21
                )));
        Assert.assertEquals(TimeBlock.Lunch, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 3,
                        12, 59
                )));


        Assert.assertEquals(TimeBlock.HomeBase, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 3,
                        10, 0
                )));
        Assert.assertEquals(TimeBlock.Red, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 3,
                        12, 0
                )));
        Assert.assertEquals(TimeBlock.Yellow, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 3,
                        13, 30
                )));
        Assert.assertEquals(TimeBlock.Green, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 3,
                        14, 30
                )));


        Assert.assertEquals(TimeBlock.HomeBase, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 4,
                        9, 30
                )));
        Assert.assertEquals(TimeBlock.Blue, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 4,
                        10, 30
                )));
        Assert.assertEquals(TimeBlock.Purple, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 4,
                        12, 0
                )));
        Assert.assertEquals(TimeBlock.Red, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 4,
                        13, 30
                )));
        Assert.assertEquals(TimeBlock.Yellow, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 4,
                        14, 30
                )));


        Assert.assertEquals(TimeBlock.Red, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 5,
                        9, 30
                )));
        Assert.assertEquals(TimeBlock.Green, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 5,
                        10, 30
                )));
        Assert.assertEquals(TimeBlock.Blue, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 5,
                        12, 0
                )));
        Assert.assertEquals(TimeBlock.Purple, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 5,
                        13, 30
                )));
        Assert.assertEquals(TimeBlock.EndOfSchool, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 5,
                        14, 30
                )));


        Assert.assertEquals(TimeBlock.HomeBase, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 6,
                        9, 30
                )));
        Assert.assertEquals(TimeBlock.Yellow, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 6,
                        10, 30
                )));
        Assert.assertEquals(TimeBlock.Green, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 6,
                        12, 0
                )));
        Assert.assertEquals(TimeBlock.Blue, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 6,
                        13, 30
                )));
        Assert.assertEquals(TimeBlock.Purple, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 6,
                        14, 30
                )));


        Assert.assertEquals(TimeBlock.HomeBase, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 7,
                        9, 30
                )));
        Assert.assertEquals(TimeBlock.HomeBase, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 7,
                        10, 30
                )));
        Assert.assertEquals(TimeBlock.Orange, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 7,
                        12, 0
                )));
        Assert.assertEquals(TimeBlock.Orange, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 7,
                        13, 30
                )));
        Assert.assertEquals(TimeBlock.Orange, TimeBlock.GetBlockAtTime(
                LocalDateTime.of(
                        2021, 5, 7,
                        14, 30
                )));
    }

}