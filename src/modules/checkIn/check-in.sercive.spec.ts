import { Test } from '@nestjs/testing';
import { getRepositoryToken } from '@nestjs/typeorm';
import { CheckInTaskService } from './service/check-in-task.service';
import { CheckIn, CheckInTask } from '@/entities/checkIn.entity';
import { Repository } from 'typeorm';
import { RepeatCycle } from '@/common/enums/repeatCycle.enum';

describe('CheckInTaskService', () => {
  let service: CheckInTaskService;
  let repo: Repository<CheckInTask>;

  beforeEach(async () => {
    const moduleRef = await Test.createTestingModule({
      providers: [
        CheckInTaskService,
        { provide: getRepositoryToken(CheckInTask), useClass: Repository },
        { provide: getRepositoryToken(CheckIn), useClass: Repository },
      ],
    }).compile();

    service = moduleRef.get<CheckInTaskService>(CheckInTaskService);
    repo = moduleRef.get<Repository<CheckInTask>>(
      getRepositoryToken(CheckInTask),
    );
  });

  describe('addCheckInTask', () => {
    it('should save and return a check in task', async () => {
      const checkInTask = {
        id: 1,
        title: 'Test Check In Task',
        RepeatCycle: RepeatCycle.DAILY,
        // repeatCycle: 'DAILY' as RepeatCycle,
      };

      jest
        .spyOn(repo.manager, 'transaction')
        .mockImplementation(() => Promise.resolve(checkInTask));

      const result = await service.createCheckInTask({
        title: checkInTask.title,
        description: 'Test Description',
        startDate: new Date(),
        repeatCycle: RepeatCycle.DAILY,
        userId: 1,
      });

      expect(result).toBeDefined();
      expect(result).toEqual(checkInTask);
    });

    it('should handle errors during task creation', async () => {
      const error = new Error('Database Error');
      jest
        .spyOn(repo.manager, 'transaction')
        .mockImplementation(() => Promise.reject(error));

      try {
        await service.createCheckInTask({
          title: 'Test Check In Task',
          description: 'Test Description',
          startDate: new Date(),
          repeatCycle: RepeatCycle.DAILY,
          userId: 1,
        });
      } catch (e) {
        expect(e).toEqual(error);
      }
    });
  });
});
