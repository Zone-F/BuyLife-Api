// schedule.service.ts
import { RepeatCycle } from '@/common/enums/repeatCycle.enum';
import { CheckIn, CheckInTask } from '@/entities/checkIn.entity';
import { Injectable } from '@nestjs/common';
import { Cron, CronExpression } from '@nestjs/schedule';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';

@Injectable()
export class ScheduleService {
  constructor(
    @InjectRepository(CheckIn)
    private readonly checkInRepository: Repository<CheckIn>,
    @InjectRepository(CheckInTask)
    private readonly taskRepository: Repository<CheckInTask>,
  ) {}
  @Cron(CronExpression.EVERY_DAY_AT_MIDNIGHT)
  async handleDayilTasks() {
    const tasksToCheckIn = await this.taskRepository.find({
      where: { status: 1, repeatCycle: RepeatCycle.DAILY },
    });

    const newCheckIns = tasksToCheckIn.map((task) => {
      const { id: taskId, userId } = task;
      const checkIn = new CheckIn();
      checkIn.taskId = taskId;
      checkIn.userId = userId;
      checkIn.status = 0;
      return checkIn;
    });

    await this.checkInRepository.save(newCheckIns);
  }
}
