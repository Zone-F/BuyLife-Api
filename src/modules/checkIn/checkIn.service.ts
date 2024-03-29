import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CheckIn, CheckInTask } from '@/entities/checkIn.entity'; // 假设你已经创建了CheckIn实体
import { CheckInDTO, CreateTaskDTO } from '@/dto/checkIn.dto';
import { RepeatCycle } from '@/common/enums/repeatCycle.enum';

@Injectable()
export class CheckInService {
  constructor(
    @InjectRepository(CheckIn)
    private readonly checkInRepository: Repository<CheckIn>,
    @InjectRepository(CheckInTask)
    private readonly taskRepository: Repository<CheckInTask>,
  ) {}
  async addCheckInTask(taskDto: CreateTaskDTO): Promise<any> {
    // 此处可能要包含一些业务逻辑，比如验证用户存在及任何其他检查
    const { userId, title, description, startDate, repeatCycle } = taskDto;

    // 创建打卡任务实体并设置属性
    const task = this.taskRepository.create({
      user: { id: userId },
      title,
      description,
      startDate,
      repeatCycle,
    });

    // 保存打卡任务实体到数据库
    const newTask = await this.taskRepository.save(task);

    // 创建任务后立即生成一条打卡记录并设为未打卡状态
    const initialCheckIn = this.checkInRepository.create({
      task: task, // 新创建的任务
      user: { id: userId }, // 打卡用户
      status: 'not_checked_in' // 初始状态
    });

    await this.checkInRepository.save(initialCheckIn);

    // 当repeatCycle为每日（DAILY）时设置定时任务自动生成打卡记录
    if (task.repeatCycle === RepeatCycle.DAILY) {
      const jobId = `task-${task.id}`;  // 定时任务ID，避免重复
      scheduleJob(jobId, '0 0 * * *', async () => {  // 每天0点触发
        const newCheckIn = this.checkInRepository.create({
          task: task,
          user: { id: userId },
          status: 'not_checked_in' // 默认状态
        });

        await this.checkInRepository.save(newCheckIn);
      });
    }
    // 返回已保存的打卡任务信息
    return newTask;
    return;
  }

  async createCheckIn(checkInDTO: CheckInDTO): Promise<CheckIn> {
    // 你需要创建一个新的CheckIn实例并保存之
    const checkIn = this.checkInRepository.create(checkInDTO);
    return this.checkInRepository.save(checkIn);
  }

  // async getCheckInsForUser(userId: number): Promise<CheckIn[]> {
  //   // 从数据库中查找并返回用户的所有打卡记录
  //   return this.checkInRepository.find({ where: { userId: userId } });
  // }

  // async undoCheckIn(checkInId: number): Promise<void> {
  //   // 找到对应的打卡记录并删除它
  //   await this.checkInRepository.delete(checkInId);
  // }

  // async getCheckInTasksForUser(userId: number): Promise<any[]> {
  //   // 返回用户打卡任务的列表，需要具体实现
  //   // 这里只是示例，实际逻辑取决于你的应用需求
  //   return []; // 示例数据
  // }

  // async removeCheckInTask(taskId: number): Promise<void> {
  //   // 找到对应的任务并删除它
  //   await this.checkInRepository.delete(taskId);
  // }

  // async reverseCheckIn(checkInId: number): Promise<CheckIn> {
  //   // 找到打卡记录，并改变其状态，这里需要你提供具体的实现
  //   return; // 示例数据
  // }
}
