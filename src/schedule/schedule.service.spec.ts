import { Test } from '@nestjs/testing';
import { getRepositoryToken } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { ScheduleService } from './schedule.service';
import { CheckIn, CheckInTask } from '@/entities/checkIn.entity';
import { RepeatCycle } from '@/common/enums/repeatCycle.enum';

describe('ScheduleService', () => {
  let service: ScheduleService;
  let taskRepository: Repository<CheckInTask>;
  let checkInRepository: Repository<CheckIn>;

  beforeEach(async () => {
    const moduleRef = await Test.createTestingModule({
      providers: [
        ScheduleService,
        { provide: getRepositoryToken(CheckIn), useClass: Repository },
        { provide: getRepositoryToken(CheckInTask), useClass: Repository },
      ],
    }).compile();

    service = moduleRef.get<ScheduleService>(ScheduleService);
    taskRepository = moduleRef.get<Repository<CheckInTask>>(
      getRepositoryToken(CheckInTask),
    );
    checkInRepository = moduleRef.get<Repository<CheckIn>>(
      getRepositoryToken(CheckIn),
    );
  });

  describe('handleDailyTasks', () => {
    it('should create new checkins for tasks with status 1 and repeat cycle DAILY', async () => {
      const tasksToCheckIn = [
        {
          id: 1,
          title: '吃饭',
          description: '每天都要吃饭',
          status: 1,
          repeatCycle: RepeatCycle.DAILY,
          userId: 1,
          startDate: new Date(1711936771233),
          point: 10,
          createTime: new Date(1711936771233),
          updateTime: new Date(1711936771233),
        },
      ];
      jest.spyOn(taskRepository, 'find').mockResolvedValue(tasksToCheckIn);
      const saveSpy = jest
        .spyOn(checkInRepository, 'save')
        .mockResolvedValue(undefined);

      await service.handleDayilTasks();

      expect(saveSpy).toHaveBeenCalledWith({
        userId: tasksToCheckIn[0].userId,
        taskId: tasksToCheckIn[0].id,
      });
    });
  });
});
